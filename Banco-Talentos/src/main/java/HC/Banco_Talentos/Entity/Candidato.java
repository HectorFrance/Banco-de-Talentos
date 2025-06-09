package HC.Banco_Talentos.Entity;

import HC.Banco_Talentos.Enum.Situacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CANDIDATOS",
        uniqueConstraints = {
                @UniqueConstraint(name = "candidato_email_unique", columnNames = "email")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    private String telefone;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false, foreignKey = @ForeignKey(name = "fk_candidato_cargo"))
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "estado_id", foreignKey = @ForeignKey(name = "fk_candidato_estado"))
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "cidade_id", foreignKey = @ForeignKey(name = "fk_candidato_cidade"))
    private Cidade cidade;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidatoSkill> candidatoSkills;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experiencia> experiencias;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anotacao> anotacoes;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "usuario_criacao_id", nullable = false, foreignKey = @ForeignKey(name = "fk_Candidato_usuario"))
    private Usuario usuarioCriacao;
}