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
@Table(name = "TECNOLOGIAS",
        uniqueConstraints = {
                @UniqueConstraint(name = "tecnologia_unique", columnNames = "nome")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @ManyToOne
    @JoinColumn(name = "usuario_criacao_id", nullable = false, foreignKey = @ForeignKey(name = "fk_tecnologia_usuario"))
    private Usuario usuarioCriacao;
}
