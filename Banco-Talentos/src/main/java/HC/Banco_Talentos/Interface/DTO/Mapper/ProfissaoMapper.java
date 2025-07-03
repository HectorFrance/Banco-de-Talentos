package HC.Banco_Talentos.Interface.DTO.Mapper;


import HC.Banco_Talentos.Interface.DTO.ProfissaoDTO;
import HC.Banco_Talentos.Domain.Entity.Profissao;
import HC.Banco_Talentos.Domain.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProfissaoMapper {

    ProfissaoMapper INSTANCE = Mappers.getMapper(ProfissaoMapper.class);

    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    ProfissaoDTO toDTO(Profissao profissao);

    @Mapping(target = "usuarioCriacao", expression = "java(usuarioFromId(dto.getUsuarioCriacao()))")
    Profissao toEntity(ProfissaoDTO dto);

    List<ProfissaoDTO> toDTO(List<Profissao> teclogiaLista);

    List<Profissao> toEntity(List<ProfissaoDTO> dtoLista);

    default Usuario usuarioFromId(Long id) {
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
