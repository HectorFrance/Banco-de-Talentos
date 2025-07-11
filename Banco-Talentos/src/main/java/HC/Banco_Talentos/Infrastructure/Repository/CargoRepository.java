package HC.Banco_Talentos.Infrastructure.Repository;

import HC.Banco_Talentos.Domain.Entity.Cargo;
import HC.Banco_Talentos.Domain.Enum.Senioridade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Optional<Cargo> findBySenioridadeAndProfissaoId(Senioridade s, Long id);

    List<Cargo> findBySenioridade(Senioridade s);

    List<Cargo> findByProfissaoId(Long id);
}
