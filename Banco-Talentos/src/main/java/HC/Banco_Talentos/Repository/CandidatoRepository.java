package HC.Banco_Talentos.Repository;

import HC.Banco_Talentos.Entity.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}
