package HC.Banco_Talentos.DTO.Mapper;

import HC.Banco_Talentos.DTO.UsuarioDTO;
import HC.Banco_Talentos.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

     Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDTO(Usuario usuario);
}
