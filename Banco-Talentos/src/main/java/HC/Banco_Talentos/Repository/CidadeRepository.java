package HC.Banco_Talentos.Repository;

import HC.Banco_Talentos.Entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {


    List<Cidade> findByEstadoId(Long id);
}
