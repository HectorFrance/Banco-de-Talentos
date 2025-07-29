package HC.Banco_Talentos.Application.Service;

import HC.Banco_Talentos.Domain.Entity.Cargo;
import HC.Banco_Talentos.Domain.Entity.Skill;
import HC.Banco_Talentos.Domain.Entity.Usuario;
import HC.Banco_Talentos.Infrastructure.Specification.CandidatoSpecification;
import HC.Banco_Talentos.Interface.DTO.Mapper.CandidatoMapper;
import HC.Banco_Talentos.Interface.DTO.Mapper.CargoMapper;
import HC.Banco_Talentos.Interface.DTO.Mapper.SkillMapper;
import HC.Banco_Talentos.Interface.DTO.Request.CandidatoRequestDTO;

import HC.Banco_Talentos.Domain.Entity.Candidato;
import HC.Banco_Talentos.Domain.Enum.Situacao;
import HC.Banco_Talentos.Shared.Exceptions.RegistroDuplicadoException;
import HC.Banco_Talentos.Infrastructure.Repository.CandidatoRepository;
import HC.Banco_Talentos.Shared.Utils.ControllerUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;
    private final CargoService cargoService;
    private final SkillService skillService;
    private final FileStorageService fileStorageService;

    public Page<Candidato> getAll(Pageable pageable) {
        return candidatoRepository.findAll(pageable);
    }

    public Candidato getByID(Long id) {
        return candidatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Candidadto não Encontrado"));
    }

    public Page<Candidato> getByTecnologia(Pageable pageable, Long idTecnologia) {
        Specification<Candidato> spec = CandidatoSpecification.comTecnologia(idTecnologia);

        return candidatoRepository.findAll(spec, pageable);
    }

    public Candidato create(CandidatoRequestDTO candidatoRequestDTO, MultipartFile curriculo) {
        Candidato candidato = CandidatoMapper.INSTANCE.toEntity(candidatoRequestDTO);

        fillCargosAndSkills(candidato);

        candidato.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());
        candidato.setSituacao(Situacao.ATIVO);

        vincularEntidadesFilhas(candidato);

        try {
            Candidato candidatoSalvo = candidatoRepository.save(candidato);
            saveCurriculo(candidatoSalvo.getId(), curriculo);
            return candidatoSalvo;

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
                String constraintName = constraintViolation.getConstraintName();

                if ("candidatos.candidato_email_unique".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Candidato já cadastrado com esse E-mail.");
                } else if ("candidatos.candidato_cpf_unique".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Candidato já cadastrado com esse CPF.");
                }
            }
            throw new RuntimeException("Erro ao salvar cndidato: " + e.getMessage(), e);
        }
    }

    public void saveCurriculo(Long idCandidato, MultipartFile curriculo) {

        Candidato candidato = candidatoRepository.findById(idCandidato).orElseThrow(() -> new EntityNotFoundException("Candidadto não Encontrado"));

        if (candidato.getCaminhoCurriculo() != null && !candidato.getCaminhoCurriculo().isBlank()) {
            fileStorageService.excluirCurriculo(candidato.getCaminhoCurriculo());
        }

        String novoCaminho = fileStorageService.salvarCurriculo(curriculo, candidato.getNome());
        candidato.setCaminhoCurriculo(novoCaminho);
        candidatoRepository.save(candidato);
    }

    private void fillCargosAndSkills(Candidato candidato) {
        candidato.setCargo(cargoService.findOrCreate(CargoMapper.INSTANCE.toDTO(candidato.getCargo())));

        if (candidato.getExperiencias() != null) {
            candidato.getExperiencias().forEach(exp -> {
                Cargo cargo = cargoService.findOrCreate(CargoMapper.INSTANCE.toDTO(exp.getCargo()));
                exp.setCargo(cargo);
            });
        }

        if (candidato.getCandidatoSkills() != null) {
            candidato.getCandidatoSkills().forEach(cs -> {
                Skill skill = skillService.findOrCreate(SkillMapper.INSTANCE.toDTO(cs.getSkill()));
                cs.setSkill(skill);
            });
        }
    }

    private void vincularEntidadesFilhas(Candidato candidato) {
        Usuario usuarioLogado = ControllerUtils.getUsuarioLogado();

        if (candidato.getCandidatoSkills() != null) {
            candidato.getCandidatoSkills().forEach(cs -> {
                cs.setCandidato(candidato);
                cs.setUsuarioCriacao(usuarioLogado);
                cs.setSituacao(Situacao.ATIVO);
            });
        }

        if (candidato.getExperiencias() != null) {
            candidato.getExperiencias().forEach(exp -> {
                exp.setCandidato(candidato);
                exp.setUsuarioCriacao(usuarioLogado);
                exp.setSituacao(Situacao.ATIVO);
            });
        }

        if (candidato.getAnotacoes() != null) {
            candidato.getAnotacoes().forEach(anot -> {
                anot.setCandidato(candidato);
                anot.setUsuarioCriacao(usuarioLogado);
                anot.setSituacao(Situacao.ATIVO);
            });
        }
    }
}
