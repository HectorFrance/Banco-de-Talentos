package HC.Banco_Talentos.Domain.Repository;

import HC.Banco_Talentos.Domain.Entity.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {

    Optional<Tecnologia> findByNomeIgnoreCase(String nome);
}
