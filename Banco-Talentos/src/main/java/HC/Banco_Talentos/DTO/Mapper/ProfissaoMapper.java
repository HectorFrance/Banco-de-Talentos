package HC.Banco_Talentos.DTO.Mapper;


import HC.Banco_Talentos.DTO.ProfissaoDTO;
import HC.Banco_Talentos.DTO.TecnologiaDTO;
import HC.Banco_Talentos.Entity.Profissao;
import HC.Banco_Talentos.Entity.Tecnologia;
import HC.Banco_Talentos.Entity.Usuario;
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
