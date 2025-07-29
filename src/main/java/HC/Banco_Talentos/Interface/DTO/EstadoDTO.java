package HC.Banco_Talentos.Interface.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "sigla")
    private String sigla;
}
