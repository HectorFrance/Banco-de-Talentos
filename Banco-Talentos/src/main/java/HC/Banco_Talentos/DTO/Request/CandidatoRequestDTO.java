package HC.Banco_Talentos.DTO.Request;

import HC.Banco_Talentos.Enum.Situacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoRequestDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "telefone")
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter entre 10 e 11 dígitos numéricos")
    private String telefone;

    @JsonProperty(value = "cidade")
    private Long cidade;

    @JsonProperty(value = "estado")
    private Long estado;

    @JsonProperty(value = "cargo")
    private Long cargo;

    @JsonProperty(value = "skills")
    private List<Long> candidatoSkills;

    @JsonProperty(value = "experiencias")
    private List<Long> experiencias;

    @JsonProperty(value = "anotacoes")
    private List<Long> anotacoes;

    @JsonProperty(value = "curriculo")
    private String caminhoCurriculo;

    @JsonProperty(value = "situacao", access = JsonProperty.Access.READ_ONLY)
    private Situacao situacao;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private Long usuarioCriacao;

}
