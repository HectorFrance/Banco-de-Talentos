package HC.Banco_Talentos.Application.Service;

import HC.Banco_Talentos.Domain.Entity.Skill;
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

    public Page<CandidatoSkill> getAll(Pageable pageable) {
        return candidatoSkillRepository.findAll(pageable);
    }

    public List<CandidatoSkill> getByCandidatoId(Long id) {
        return candidatoSkillRepository.findByCandidatoId(id);
    }

    public CandidatoSkill findById(Long id) {
        return candidatoSkillRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Skill não encontrada"));
    }

    public CandidatoSkill create(CandidatoSkillRequestDTO candidatoSkillRequestDTO) {
        Skill skill = skillService.findOrCreate(candidatoSkillRequestDTO.getSkill());

        CandidatoSkill candidatoSkill = CandidatoSkillMapper.INSTANCE.toEntity(candidatoSkillRequestDTO);

        candidatoSkill.setSkill(skill);
        candidatoSkill.setSituacao(Situacao.ATIVO);
        candidatoSkill.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        try {
            CandidatoSkill candidatoSkillSalvoSalva = candidatoSkillRepository.save(candidatoSkill);
            return candidatoSkillSalvoSalva;

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
