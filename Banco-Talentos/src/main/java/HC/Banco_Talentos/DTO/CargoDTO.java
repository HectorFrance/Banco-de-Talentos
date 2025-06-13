package HC.Banco_Talentos.DTO;

import HC.Banco_Talentos.Enum.Senioridade;
import HC.Banco_Talentos.Enum.Situacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty("profissao")
    private ProfissaoDTO profissaoDTO;

    @JsonProperty("senioridade")
    private Senioridade senioridade;

    @JsonProperty(value = "situacao", access = JsonProperty.Access.READ_ONLY)
    private Situacao situacao;

    @JsonProperty(value = "data-cadastro", access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataCadastro;

    @JsonProperty(value = "usuario-criacao", access = JsonProperty.Access.READ_ONLY)
    private UsuarioDTO usuarioCriacao;
}
