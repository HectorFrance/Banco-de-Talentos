package HC.Banco_Talentos.DTO;

import HC.Banco_Talentos.Enum.Role;
import HC.Banco_Talentos.Enum.Situacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty(value = "senha", access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @JsonProperty(value = "sit-cad", access = JsonProperty.Access.READ_ONLY)
    private Situacao situacao;

    @JsonProperty(value = "dat-cad", access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private Role role;
}
