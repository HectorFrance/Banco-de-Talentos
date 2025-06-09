package HC.Banco_Talentos.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "ESTADOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estado {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 2, unique = true)
    private String sigla;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private List<Cidade> cidades;
}
