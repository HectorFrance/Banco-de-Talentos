package HC.Banco_Talentos.DTO.Response;

import HC.Banco_Talentos.DTO.SkillResponseDTO;
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
public class CandidatoSkillResponseDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "candidato")
    private Long candidato;

    @JsonProperty(value = "skill")
    private SkillResponseDTO skill;

    @JsonProperty(value = "situacao", access = JsonProperty.Access.READ_ONLY)
    private Situacao situacao;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private Long usuarioCriacao;
}
