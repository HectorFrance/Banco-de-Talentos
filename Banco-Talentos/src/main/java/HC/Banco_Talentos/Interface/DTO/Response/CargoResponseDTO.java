package HC.Banco_Talentos.Interface.DTO.Response;

import HC.Banco_Talentos.Interface.DTO.ProfissaoDTO;
import HC.Banco_Talentos.Domain.Enum.Senioridade;
import HC.Banco_Talentos.Domain.Enum.Situacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoResponseDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty("profissao")
    private ProfissaoDTO profissao;

    @JsonProperty("senioridade")
    private Senioridade senioridade;

    @JsonProperty(value = "situacao", access = JsonProperty.Access.READ_ONLY)
    private Situacao situacao;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private Long usuarioCriacao;

}
