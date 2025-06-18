package HC.Banco_Talentos.DTO.Mapper;

import HC.Banco_Talentos.DTO.CidadeDTO;
import HC.Banco_Talentos.Entity.Candidato;
import HC.Banco_Talentos.Entity.Cidade;
import HC.Banco_Talentos.Entity.Estado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {EstadoMapper.class})
public interface CidadeMapper {

    CidadeMapper INSTANCE = Mappers.getMapper(CidadeMapper.class);

    @Mapping(source = "estado", target = "estado")
    CidadeDTO toDTO(Cidade cidade);

    @Mapping(source = "estado", target = "estado")
    Cidade toEntity(CidadeDTO dto);

    List<CidadeDTO> toDTO(List<Cidade> cidades);

    List<Cidade> toEntity(List<CidadeDTO> dto);

}
