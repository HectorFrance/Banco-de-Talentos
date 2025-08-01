package HC.Banco_Talentos.Interface.DTO.Mapper;

import HC.Banco_Talentos.Interface.DTO.Request.ExperienciaRequestDTO;
import HC.Banco_Talentos.Interface.DTO.Response.ExperienciaResponseDTO;
import HC.Banco_Talentos.Domain.Entity.Candidato;
import HC.Banco_Talentos.Domain.Entity.Cargo;
import HC.Banco_Talentos.Domain.Entity.Experiencia;
import HC.Banco_Talentos.Domain.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses =  {CargoMapper.class})
public interface ExperienciaMapper {

    ExperienciaMapper INSTANCE = Mappers.getMapper(ExperienciaMapper.class);

    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    @Mapping(source = "candidato.id", target = "candidato")
    @Mapping(source = "cargo", target = "cargo")
    ExperienciaRequestDTO toDTO(Experiencia experiencia);

    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    @Mapping(source = "candidato.id", target = "candidato")
    @Mapping(source = "cargo", target = "cargo")
    ExperienciaResponseDTO toResponseDTO(Experiencia experiencia);

    @Mapping(target = "usuarioCriacao", expression = "java(usuarioFromId(dto.getUsuarioCriacao()))")
    @Mapping(target = "candidato", expression = "java(candidatoFromId(dto.getCandidato()))")
    @Mapping(source = "cargo", target = "cargo")
    Experiencia toEntity(ExperienciaRequestDTO dto);

    List<ExperienciaRequestDTO> toDTO(List<Experiencia> experiencias);

    List<ExperienciaResponseDTO> toResponseDTO(List<Experiencia> experiencias);

    List<Experiencia> toEntity(List<ExperienciaRequestDTO> dto);

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

    default Cargo cargoFromId(Long id) {
        if (id == null) return null;
        Cargo c = new Cargo();
        c.setId(id);
        return c;
    }
}
