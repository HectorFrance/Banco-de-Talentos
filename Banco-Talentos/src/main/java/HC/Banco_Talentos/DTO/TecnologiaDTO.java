package HC.Banco_Talentos.DTO;

import HC.Banco_Talentos.Entity.Usuario;
import HC.Banco_Talentos.Enum.Situacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnologiaDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty(value = "situacao", access = JsonProperty.Access.READ_ONLY)
    private Situacao situacao;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private Long usuarioCriacao;
}
