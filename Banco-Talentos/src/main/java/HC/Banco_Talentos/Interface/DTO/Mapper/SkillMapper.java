package HC.Banco_Talentos.Interface.DTO.Mapper;

import HC.Banco_Talentos.Interface.DTO.Request.SkillRequestDTO;
import HC.Banco_Talentos.Interface.DTO.Response.SkillResponseDTO;
import HC.Banco_Talentos.Domain.Entity.Skill;
import HC.Banco_Talentos.Domain.Entity.Tecnologia;
import HC.Banco_Talentos.Domain.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses =  {TecnologiaMapper.class})
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    @Mapping(source = "tecnologia.id", target = "tecnologia")
    SkillRequestDTO toDTO(Skill skill);

    @Mapping(source = "tecnologia", target = "tecnologia")
    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    SkillResponseDTO toResponseDTO(Skill skill);

    @Mapping(target = "usuarioCriacao", expression = "java(usuarioFromId(dto.getUsuarioCriacao()))")
    @Mapping(target = "tecnologia", expression = "java(tecnologiaFromId(dto.getTecnologia()))")
    Skill toEntity(SkillRequestDTO dto);

    List<SkillRequestDTO> toDTO(List<Skill> skill);

    List<SkillResponseDTO> toResponseDTO(List<Skill> skill);

    List<Skill> toEntity(List<SkillRequestDTO> skillRequestDTOS);

    default Usuario usuarioFromId(Long id) {
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
    default Tecnologia tecnologiaFromId(Long id) {
        if (id == null) return null;
        Tecnologia t = new Tecnologia();
        t.setId(id);
        return t;
    }
}
