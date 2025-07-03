package HC.Banco_Talentos.Domain.Repository;

import HC.Banco_Talentos.Domain.Entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {


    List<Cidade> findByEstadoId(Long id);
}
