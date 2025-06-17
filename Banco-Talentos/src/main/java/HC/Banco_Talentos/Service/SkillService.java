package HC.Banco_Talentos.Service;

import HC.Banco_Talentos.DTO.Mapper.SkillMapper;
import HC.Banco_Talentos.DTO.SkillDTO;
import HC.Banco_Talentos.Entity.Skill;
import HC.Banco_Talentos.Enum.Situacao;
import HC.Banco_Talentos.Exceptions.RegistroDuplicadoException;
import HC.Banco_Talentos.Repository.SkillRepository;
import HC.Banco_Talentos.Utils.ControllerUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final TecnologiaService tecnologiaService;

    public List<SkillDTO> getAll() {
        return SkillMapper.INSTANCE.toDTO(skillRepository.findAll());
    }

    public SkillDTO findById(Long id) {
        return SkillMapper.INSTANCE.toDTO(skillRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Skill Não encontrada")));
    }

    public SkillDTO create(SkillDTO skillDTO) {
        Skill skill = SkillMapper.INSTANCE.toEntity(skillDTO);
        skill.setSituacao(Situacao.ATIVO);
        skill.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        try{
            return SkillMapper.INSTANCE.toDTO(skillRepository.save(skill));
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
}
