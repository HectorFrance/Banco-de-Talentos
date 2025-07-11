package HC.Banco_Talentos.Interface.DTO.Request;

import HC.Banco_Talentos.Domain.Enum.Nivel;
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
public class SkillRequestDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty("tecnologia")
    private Long tecnologia;

    @JsonProperty("nivel")
    private Nivel nivel;

    @JsonProperty(value = "situacao", access = JsonProperty.Access.READ_ONLY)
    private Situacao situacao;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private Long usuarioCriacao;
}
