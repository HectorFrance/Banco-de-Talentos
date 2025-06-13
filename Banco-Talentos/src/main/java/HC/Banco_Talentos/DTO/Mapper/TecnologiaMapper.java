package HC.Banco_Talentos.DTO.Mapper;

import HC.Banco_Talentos.DTO.TecnologiaDTO;
import HC.Banco_Talentos.Entity.Tecnologia;

public class TecnologiaMapper {

    public static TecnologiaDTO toDTO(Tecnologia tecnologia){
        TecnologiaDTO dto = new TecnologiaDTO();

        dto.setId(tecnologia.getId());
        dto.setNome(tecnologia.getNome());
        dto.setDataCadastro(tecnologia.getDataCadastro());
        dto.setSituacao(tecnologia.getSituacao());
        dto.setUsuarioCriacao(
                tecnologia.getUsuarioCriacao() != null?
                UsuarioMapper.toDTO(tecnologia.getUsuarioCriacao()) : null
        );

        return dto;
    }

    public static Tecnologia toEntity(TecnologiaDTO dto){
        Tecnologia tecnologia = new Tecnologia();

        tecnologia.setId(dto.getId());
        tecnologia.setNome(dto.getNome());
        tecnologia.setDataCadastro(dto.getDataCadastro());
        tecnologia.setSituacao(dto.getSituacao());
        tecnologia.setUsuarioCriacao(
                dto.getUsuarioCriacao() != null ?
                UsuarioMapper.toEntity(dto.getUsuarioCriacao()) : null
        );

        return tecnologia;
    }
}
