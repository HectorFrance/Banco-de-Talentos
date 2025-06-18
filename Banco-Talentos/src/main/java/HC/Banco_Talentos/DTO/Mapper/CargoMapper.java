package HC.Banco_Talentos.DTO.Mapper;


import HC.Banco_Talentos.DTO.Request.CargoRequestDTO;
import HC.Banco_Talentos.DTO.Response.CargoResponseDTO;
import HC.Banco_Talentos.Entity.Cargo;
import HC.Banco_Talentos.Entity.Profissao;
import HC.Banco_Talentos.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ProfissaoMapper.class})
public interface CargoMapper {

    CargoMapper INSTANCE = Mappers.getMapper(CargoMapper.class);

    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    @Mapping(source = "profissao.id", target = "profissao")
    CargoRequestDTO toDTO(Cargo cargo);

    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    @Mapping(source = "profissao", target = "profissao")
    CargoResponseDTO toResponseDTO(Cargo cargo);

    @Mapping(target = "usuarioCriacao", expression = "java(usuarioFromId(dto.getUsuarioCriacao()))")
    @Mapping(target = "profissao", expression = "java(profissaoFromId(dto.getProfissao()))")
    Cargo toEntity(CargoRequestDTO dto);

    List<CargoRequestDTO> toDTO(List<Cargo> cargos);

    List<CargoResponseDTO> toResponseDTO(List<Cargo> cargo);

    List<Cargo> toEntity(List<CargoRequestDTO> DTOs);

    default Usuario usuarioFromId(Long id) {
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }

    default Profissao profissaoFromId(Long id) {
        if (id == null) return null;
        Profissao p = new Profissao();
        p.setId(id);
        return p;
    }
}
