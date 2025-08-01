package HC.Banco_Talentos.Interface.DTO.Request;

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
public class CandidatoSkillRequestDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "candidato")
    private Long candidato;

    @JsonProperty(value = "skill")
    private SkillRequestDTO skill;

    @JsonProperty(value = "situacao", access = JsonProperty.Access.READ_ONLY)
    private Situacao situacao;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private Long usuarioCriacao;
}
