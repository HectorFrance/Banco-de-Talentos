package HC.Banco_Talentos.DTO.Mapper;

import HC.Banco_Talentos.DTO.Request.CandidatoSkillRequestDTO;
import HC.Banco_Talentos.DTO.Response.CandidatoSkillResponseDTO;
import HC.Banco_Talentos.Entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {SkillMapper.class})
public interface CandidatoSkillMapper {

    CandidatoSkillMapper INSTANCE = Mappers.getMapper(CandidatoSkillMapper.class);

    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    @Mapping(source = "candidato.id", target = "candidato")
    @Mapping(source = "skill.id", target = "skill")
    CandidatoSkillRequestDTO toDTO(CandidatoSkill candidatoSkill);

    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    @Mapping(source = "candidato.id", target = "candidato")
    @Mapping(source = "skill", target = "skill")
    CandidatoSkillResponseDTO toResponseDTO(CandidatoSkill candidatoSkill);

    @Mapping(target = "usuarioCriacao", expression = "java(usuarioFromId(dto.getUsuarioCriacao()))")
    @Mapping(target = "candidato", expression = "java(candidatoFromId(dto.getCandidato()))")
    @Mapping(target = "skill", expression = "java(skillFromId(dto.getSkill()))")
    CandidatoSkill toEntity(CandidatoSkillRequestDTO dto);

    List<CandidatoSkillRequestDTO> toDTO(List<CandidatoSkill> candidatoSkills);

    List<CandidatoSkillResponseDTO> toResponseDTO(List<CandidatoSkill> candidatoSkill);

    List<CandidatoSkill> toEntity(List<CandidatoSkillRequestDTO> dto);

    default Usuario usuarioFromId(Long id) {
        if (id == null) return null;
        Usuario u = new Usuario();
        u.setId(id);
        return u;
    }

    default Candidato candidatoFromId(Long id) {
        if (id == null) return null;
        Candidato c = new Candidato();
        c.setId(id);
        return c;
    }

    default Skill skillFromId(Long id) {
        if (id == null) return null;
        Skill s = new Skill();
        s.setId(id);
        return s;
    }
}
