package HC.Banco_Talentos.Entity;

import HC.Banco_Talentos.Enum.Situacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ANOTACOES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "candidato_id", nullable = false, foreignKey = @ForeignKey(name = "fk_anotacao_candidato"))
    private Candidato candidato;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "usuario_criacao_id", nullable = false, foreignKey = @ForeignKey(name = "fk_anotacao_usuario"))
    private Usuario usuarioCriacao;
}
