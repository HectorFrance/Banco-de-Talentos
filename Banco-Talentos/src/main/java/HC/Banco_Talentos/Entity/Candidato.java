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
                @UniqueConstraint(name = "candidato_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "candidato_cpf_unique", columnNames = "cpf")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "cidade_id", foreignKey = @ForeignKey(name = "fk_candidato_cidade"))
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "estado_id", foreignKey = @ForeignKey(name = "fk_candidato_estado"))
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false, foreignKey = @ForeignKey(name = "fk_candidato_cargo"))
    private Cargo cargo;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidatoSkill> candidatoSkills;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experiencia> experiencias;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anotacao> anotacoes;

    @Column(name = "caminho_curriculo")
    private String caminhoCurriculo;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "usuario_criacao_id", nullable = false, foreignKey = @ForeignKey(name = "fk_Candidato_usuario"))
    private Usuario usuarioCriacao;
}