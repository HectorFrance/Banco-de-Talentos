package HC.Banco_Talentos.Domain.Entity;

import HC.Banco_Talentos.Domain.Enum.Senioridade;
import HC.Banco_Talentos.Domain.Enum.Situacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "CARGOS",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_profissao_senioridade",
                        columnNames = {"profissao_id", "senioridade"}
                )
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profissao_id", nullable = false, foreignKey = @ForeignKey(name = "fk_cargo_profissao"))
    private Profissao profissao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Senioridade senioridade;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "usuario_criacao_id", nullable = false, foreignKey = @ForeignKey(name = "fk_profissao_usuario"))
    private Usuario usuarioCriacao;
}

