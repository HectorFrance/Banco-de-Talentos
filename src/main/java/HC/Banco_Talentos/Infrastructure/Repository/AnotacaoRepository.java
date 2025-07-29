package HC.Banco_Talentos.Infrastructure.Repository;

import HC.Banco_Talentos.Domain.Entity.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {
}
