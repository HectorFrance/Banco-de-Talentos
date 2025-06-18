package HC.Banco_Talentos.DTO.Mapper;

import HC.Banco_Talentos.DTO.AnotacaoDTO;
import HC.Banco_Talentos.Entity.Anotacao;
import HC.Banco_Talentos.Entity.Candidato;
import HC.Banco_Talentos.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnotacaoMapper {

    AnotacaoMapper INSTANCE = Mappers.getMapper(AnotacaoMapper.class);

    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    @Mapping(source = "candidato.id", target = "candidato")
    AnotacaoDTO toDTO(Anotacao anotacao);

    @Mapping(target = "usuarioCriacao", expression = "java(usuarioFromId(dto.getUsuarioCriacao()))")
    @Mapping(target = "candidato", expression = "java(candidatoFromId(dto.getCandidato()))")
    Anotacao toEntity(AnotacaoDTO dto);

    List<AnotacaoDTO> toDTO(List<Anotacao> anotacao);

    List<Anotacao> toEntity(List<AnotacaoDTO> dto);

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
}
