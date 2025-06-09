package HC.Banco_Talentos.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CIDADES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cidade {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estados_id", nullable = false)
    private Estado estado;
}
