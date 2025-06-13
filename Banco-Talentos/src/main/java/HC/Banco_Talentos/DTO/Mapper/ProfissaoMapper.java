package HC.Banco_Talentos.DTO.Mapper;


import HC.Banco_Talentos.DTO.ProfissaoDTO;
import HC.Banco_Talentos.Entity.Profissao;

public class ProfissaoMapper {

    public static ProfissaoDTO toDTO(Profissao profissao){
        ProfissaoDTO dto = new ProfissaoDTO();

        dto.setId(profissao.getId());
        dto.setNome(profissao.getNome());
        dto.setDataCadastro(profissao.getDataCadastro());
        dto.setSituacao(profissao.getSituacao());
        dto.setUsuarioCriacao(
                profissao.getUsuarioCriacao() != null ?
                        UsuarioMapper.toDTO(profissao.getUsuarioCriacao()) : null
        );

        return dto;
    }

    public static Profissao toEntity(ProfissaoDTO dto){
        Profissao profissao = new Profissao();

        profissao.setId(dto.getId());
        profissao.setNome(dto.getNome());
        profissao.setDataCadastro(dto.getDataCadastro());
        profissao.setSituacao(dto.getSituacao());
        profissao.setUsuarioCriacao(
                dto.getUsuarioCriacao() != null ?
                UsuarioMapper.toEntity(dto.getUsuarioCriacao()) : null
        );

        return profissao;
    }
}
