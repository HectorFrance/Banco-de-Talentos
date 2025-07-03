package HC.Banco_Talentos.Domain.Repository;

import HC.Banco_Talentos.Domain.Entity.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}
