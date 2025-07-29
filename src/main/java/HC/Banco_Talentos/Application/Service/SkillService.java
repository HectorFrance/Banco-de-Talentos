package HC.Banco_Talentos.Application.Service;

import HC.Banco_Talentos.Interface.DTO.Mapper.SkillMapper;
import HC.Banco_Talentos.Interface.DTO.Request.SkillRequestDTO;
import HC.Banco_Talentos.Interface.DTO.Response.SkillResponseDTO;
import HC.Banco_Talentos.Domain.Entity.Skill;
import HC.Banco_Talentos.Domain.Enum.Situacao;
import HC.Banco_Talentos.Shared.Exceptions.RegistroDuplicadoException;
import HC.Banco_Talentos.Infrastructure.Repository.SkillRepository;
import HC.Banco_Talentos.Shared.Utils.ControllerUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final TecnologiaService tecnologiaService;

    public List<Skill> getAll() {
        return skillRepository.findAll();
    }

    public Skill findById(Long id) {
        return skillRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Skill Não encontrada"));
    }

    public Skill create(SkillRequestDTO skillRequestDTO) {
        Skill skill = SkillMapper.INSTANCE.toEntity(skillRequestDTO);
        skill.setSituacao(Situacao.ATIVO);
        skill.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        try{
            return skillRepository.save(skill);
        }catch (DataIntegrityViolationException e){
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation){
                String constraintName = constraintViolation.getConstraintName();

                if ("skills.uk_skill_tecnologia_nivel".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Skill já cadastrada.");
                }
            }
            throw new RuntimeException("Erro ao salvar Skill: " + e.getMessage(), e);
        }
    }

    public Skill findOrCreate(SkillRequestDTO skillRequestDTO){
        Optional<Skill> skillOptional = skillRepository.findByTecnologiaIdAndNivel(skillRequestDTO.getTecnologia(),skillRequestDTO.getNivel());
        return skillOptional.orElseGet(() -> create(skillRequestDTO));
    }
}
