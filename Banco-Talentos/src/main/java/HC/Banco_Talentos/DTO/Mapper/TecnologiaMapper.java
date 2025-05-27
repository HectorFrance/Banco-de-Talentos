package HC.Banco_Talentos.DTO.Mapper;

import HC.Banco_Talentos.DTO.TecnologiaDTO;
import HC.Banco_Talentos.Entity.Tecnologia;

public class TecnologiaMapper {

    public static TecnologiaDTO toDTO(Tecnologia tecnologia){
        TecnologiaDTO dto = new TecnologiaDTO();

        dto.setId(tecnologia.getId());
        dto.setNome(tecnologia.getNome());
        dto.setDataCriacao(tecnologia.getDataCriacao());
        dto.setSituacao(tecnologia.getSituacao());
        dto.setUsuarioCriacao(UsuarioMapper.toDTO(tecnologia.getUsuarioCriacao()));

        return dto;
    }

    public static Tecnologia toEntity(TecnologiaDTO dto){
        Tecnologia tecnologia = new Tecnologia();

        tecnologia.setId(dto.getId());
        tecnologia.setNome(dto.getNome());
        tecnologia.setDataCriacao(dto.getDataCriacao());
        tecnologia.setSituacao(dto.getSituacao());
        tecnologia.setUsuarioCriacao(UsuarioMapper.toEntity(dto.getUsuarioCriacao()));

        return tecnologia;
    }
}
