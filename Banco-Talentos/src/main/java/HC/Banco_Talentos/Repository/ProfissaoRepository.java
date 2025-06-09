package HC.Banco_Talentos.Repository;

import HC.Banco_Talentos.Entity.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {
}
