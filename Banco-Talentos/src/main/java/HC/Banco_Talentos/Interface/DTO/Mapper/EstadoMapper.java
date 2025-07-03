package HC.Banco_Talentos.Interface.DTO.Mapper;

import HC.Banco_Talentos.Interface.DTO.EstadoDTO;
import HC.Banco_Talentos.Domain.Entity.Estado;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EstadoMapper {

    EstadoMapper INSTANCE = Mappers.getMapper(EstadoMapper.class);

    EstadoDTO toDTO(Estado estado);

    Estado toEntity(EstadoDTO dto);

    List<EstadoDTO> toDTO(List<Estado> estados);

    List<Estado> toEntity(List<EstadoDTO> dtos);
}
