package HC.Banco_Talentos.Domain.Entity;

import HC.Banco_Talentos.Domain.Enum.Situacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "PROFISSOES",
        uniqueConstraints = {
                @UniqueConstraint(name = "profissao_unique", columnNames = "nome")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "usuario_criacao_id", nullable = false, foreignKey = @ForeignKey(name = "fk_profissao_usuario_criacao"))
    private Usuario usuarioCriacao;
}
