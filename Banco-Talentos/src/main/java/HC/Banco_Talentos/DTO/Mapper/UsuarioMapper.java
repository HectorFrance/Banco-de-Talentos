package HC.Banco_Talentos.DTO.Mapper;

import HC.Banco_Talentos.DTO.UsuarioDTO;
import HC.Banco_Talentos.Entity.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO dto){
        if(dto == null)
            return null;

        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setCpf(dto.getCpf());
        usuario.setSenha(dto.getSenha());
        usuario.setSituacao(dto.getSituacao());
        usuario.setDataCadastro(dto.getDataCadastro());
        usuario.setRole(dto.getRole());

        return usuario;
    }

    public static UsuarioDTO toDTO(Usuario usuario){
        if(usuario == null)
            return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setCpf(usuario.getCpf());
        dto.setSenha(usuario.getSenha());
        dto.setSituacao(usuario.getSituacao());
        dto.setDataCadastro(usuario.getDataCadastro());
        dto.setRole(usuario.getRole());

        return dto;
    }
}
