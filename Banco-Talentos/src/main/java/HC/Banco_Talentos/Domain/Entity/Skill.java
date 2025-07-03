package HC.Banco_Talentos.Domain.Entity;

import HC.Banco_Talentos.Domain.Enum.Nivel;
import HC.Banco_Talentos.Domain.Enum.Situacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "SKILLS", uniqueConstraints = {
        @UniqueConstraint(name = "uk_skill_tecnologia_nivel", columnNames = {"tecnologia_id", "nivel"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tecnologia_id", nullable = false, foreignKey = @ForeignKey(name = "fk_skill_tecnologia"))
    private Tecnologia tecnologia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Nivel nivel;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_criacao_id", foreignKey = @ForeignKey(name = "fk_skill_usuario"))
    private Usuario usuarioCriacao;
}

