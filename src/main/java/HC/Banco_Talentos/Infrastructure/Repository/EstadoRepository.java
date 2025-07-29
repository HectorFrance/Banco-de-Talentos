package HC.Banco_Talentos.Infrastructure.Repository;

import HC.Banco_Talentos.Domain.Entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
