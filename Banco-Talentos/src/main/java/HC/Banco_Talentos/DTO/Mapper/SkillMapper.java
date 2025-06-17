package HC.Banco_Talentos.DTO.Mapper;

import HC.Banco_Talentos.DTO.SkillDTO;
import HC.Banco_Talentos.Entity.Skill;
import HC.Banco_Talentos.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses =  {TecnologiaMapper.class})
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    SkillDTO toDTO(Skill skill);

    @Mapping(target = "usuarioCriacao", expression = "java(usuarioFromId(dto.getUsuarioCriacao()))")
    Skill toEntity(SkillDTO dto);

    List<SkillDTO> toDTO(List<Skill> skill);

    List<Skill> toEntity(List<SkillDTO> skillDTOS);

    default Usuario usuarioFromId(Long id) {
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
