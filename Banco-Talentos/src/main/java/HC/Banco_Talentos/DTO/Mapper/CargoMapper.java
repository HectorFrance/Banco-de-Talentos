package HC.Banco_Talentos.DTO.Mapper;


import HC.Banco_Talentos.DTO.CargoDTO;
import HC.Banco_Talentos.Entity.Cargo;
import HC.Banco_Talentos.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ProfissaoMapper.class})
public interface CargoMapper {

    CargoMapper INSTANCE = Mappers.getMapper(CargoMapper.class);

    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    CargoDTO toDTO(Cargo cargo);

    @Mapping(target = "usuarioCriacao", expression = "java(usuarioFromId(dto.getUsuarioCriacao()))")
    Cargo toEntity(CargoDTO dto);

    List<CargoDTO> toDTO(List<Cargo> cargos);

    List<Cargo> toEntity(List<CargoDTO> DTOs);

    default Usuario usuarioFromId(Long id) {
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
