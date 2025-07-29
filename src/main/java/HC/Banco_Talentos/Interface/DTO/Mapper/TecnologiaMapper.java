package HC.Banco_Talentos.Interface.DTO.Mapper;

import HC.Banco_Talentos.Interface.DTO.TecnologiaDTO;
import HC.Banco_Talentos.Domain.Entity.Tecnologia;
import HC.Banco_Talentos.Domain.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TecnologiaMapper {

    TecnologiaMapper INSTANCE = Mappers.getMapper(TecnologiaMapper.class);

    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    TecnologiaDTO toDTO(Tecnologia tecnologia);

    @Mapping(target = "usuarioCriacao", expression = "java(usuarioFromId(dto.getUsuarioCriacao()))")
    Tecnologia toEntity(TecnologiaDTO dto);

    List<TecnologiaDTO> toDTO(List<Tecnologia> teclogiaLista);

    List<Tecnologia> toEntity(List<TecnologiaDTO> dtoLista);

    default Usuario usuarioFromId(Long id) {
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
