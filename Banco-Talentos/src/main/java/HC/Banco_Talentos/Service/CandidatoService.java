package HC.Banco_Talentos.Service;

import HC.Banco_Talentos.DTO.Mapper.CandidatoMapper;
import HC.Banco_Talentos.DTO.Mapper.CargoMapper;
import HC.Banco_Talentos.DTO.Request.CandidatoRequestDTO;
import HC.Banco_Talentos.DTO.Response.CandidatoResponseDTO;
import HC.Banco_Talentos.Entity.Candidato;
import HC.Banco_Talentos.Enum.Situacao;
import HC.Banco_Talentos.Exceptions.RegistroDuplicadoException;
import HC.Banco_Talentos.Repository.CandidatoRepository;
import HC.Banco_Talentos.Utils.ControllerUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;
    private final EstadoService estadoService;
    private final CidadeService cidadeService;
    private final CargoService cargoService;
    private final CandidatoSkillService candidatoSkillService;
    private final ExperienciaService experienciaService;

    public Page<CandidatoResponseDTO> getAll(Pageable pageable){
        return candidatoRepository.findAll(pageable).map(CandidatoMapper.INSTANCE :: toResponseDTO);
    }

    public CandidatoResponseDTO getByID(Long id){
        return CandidatoMapper.INSTANCE.toResponseDTO(candidatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Candidadto não Encontrado")));
    }

    public CandidatoResponseDTO create(CandidatoRequestDTO candidatoRequestDTO){
        candidatoRequestDTO.setCargo(cargoService.findOrCreate(candidatoRequestDTO.getCargo()));
        Candidato candidato = CandidatoMapper.INSTANCE.toEntity(candidatoRequestDTO);
        candidato.setSituacao(Situacao.ATIVO);
        candidato.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        if (candidato.getCandidatoSkills() != null) {
            candidato.getCandidatoSkills().forEach(skill -> skill.setCandidato(candidato));
        }

        if (candidato.getExperiencias() != null) {
            candidato.getExperiencias().forEach(experiencia -> experiencia.setCandidato(candidato));
        }

        if (candidato.getAnotacoes() != null) {
            candidato.getAnotacoes().forEach(anotacao -> anotacao.setCandidato(candidato));
        }

        try {
            return CandidatoMapper.INSTANCE.toResponseDTO(candidatoRepository.save(candidato));
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
                String constraintName = constraintViolation.getConstraintName();

                if ("candidadtos.candidato_email_uniquee".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Candidato já cadastrado.");
                }
            }
            throw new RuntimeException("Erro ao salvar cndidato: " + e.getMessage(), e);
        }
    }
}
