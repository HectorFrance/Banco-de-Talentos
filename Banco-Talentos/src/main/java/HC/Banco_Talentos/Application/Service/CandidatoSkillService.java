package HC.Banco_Talentos.Application.Service;

import HC.Banco_Talentos.Interface.DTO.Mapper.CandidatoSkillMapper;
import HC.Banco_Talentos.Interface.DTO.Request.CandidatoSkillRequestDTO;
import HC.Banco_Talentos.Interface.DTO.Request.SkillRequestDTO;
import HC.Banco_Talentos.Interface.DTO.Response.CandidatoSkillResponseDTO;
import HC.Banco_Talentos.Domain.Entity.CandidatoSkill;
import HC.Banco_Talentos.Domain.Enum.Situacao;
import HC.Banco_Talentos.Shared.Exceptions.RegistroDuplicadoException;
import HC.Banco_Talentos.Infrastructure.Repository.CandidatoSkillRepository;
import HC.Banco_Talentos.Shared.Utils.ControllerUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidatoSkillService {

    private final CandidatoSkillRepository candidatoSkillRepository;
    private final SkillService skillService;

    public Page<CandidatoSkillResponseDTO> getAll(Pageable pageable) {
        return candidatoSkillRepository.findAll(pageable).map(CandidatoSkillMapper.INSTANCE::toResponseDTO);
    }

    public List<CandidatoSkillResponseDTO> getByCandidatoId(Long id) {
        return CandidatoSkillMapper.INSTANCE.toResponseDTO(candidatoSkillRepository.findByCandidatoId(id));
    }

    public CandidatoSkillResponseDTO findById(Long id) {
        return CandidatoSkillMapper.INSTANCE.toResponseDTO(candidatoSkillRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Skill não encontrada")));
    }

    public CandidatoSkillResponseDTO create(CandidatoSkillRequestDTO candidatoSkillRequestDTO) {
        SkillRequestDTO skill = skillService.findOrCreate(candidatoSkillRequestDTO.getSkill());
        candidatoSkillRequestDTO.setSkill(skill);

        CandidatoSkill candidatoSkill = CandidatoSkillMapper.INSTANCE.toEntity(candidatoSkillRequestDTO);
        candidatoSkill.setSituacao(Situacao.ATIVO);
        candidatoSkill.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        try {
            CandidatoSkill candidatoSkillSalvoSalva = candidatoSkillRepository.save(candidatoSkill);
            return CandidatoSkillMapper.INSTANCE.toResponseDTO(candidatoSkillSalvoSalva);

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
                String constraintName = constraintViolation.getConstraintName();

                if ("candidato_skills.uk_candidato_skill".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Candidato já possui essa Skill");
                }
            }
            throw new RuntimeException("Erro ao salvar cndidato: " + e.getMessage(), e);
        }
    }
}
