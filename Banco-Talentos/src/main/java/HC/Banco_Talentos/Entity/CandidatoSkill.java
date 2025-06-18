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
@Table(name = "CANDIDATO_SKILLS", uniqueConstraints = {
        @UniqueConstraint(name = "uk_candidato_skill", columnNames = {"candidato_id", "skill_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidatoSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "candidato_id", nullable = false, foreignKey = @ForeignKey(name = "fk_candidato_skill_candidato"))
    private Candidato candidato;

    @ManyToOne(optional = false)
    @JoinColumn(name = "skill_id", nullable = false, foreignKey = @ForeignKey(name = "fk_candidato_skill_skill"))
    private Skill skill;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_criacao_id", foreignKey = @ForeignKey(name = "fk_candidato_skill_usuario"))
    private Usuario usuarioCriacao;
}

