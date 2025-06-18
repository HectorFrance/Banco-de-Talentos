package HC.Banco_Talentos.DTO.Response;

import HC.Banco_Talentos.DTO.*;
import HC.Banco_Talentos.DTO.AnotacaoDTO;
import HC.Banco_Talentos.Enum.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    private CidadeDTO cidade;
    private EstadoDTO estado;
    private CargoResponseDTO cargo;

    private List<CandidatoSkillResponseDTO> candidatoSkills;
    private List<ExperienciaResponseDTO> experiencias;
    private List<AnotacaoDTO> anotacoes;

    private String caminhoCurriculo;
    private Situacao situacao;
    private LocalDateTime dataCadastro;

    private Long usuarioCriacao;
}
