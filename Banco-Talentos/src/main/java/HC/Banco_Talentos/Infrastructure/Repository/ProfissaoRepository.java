package HC.Banco_Talentos.Infrastructure.Repository;

import HC.Banco_Talentos.Domain.Entity.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {

    Optional<Profissao> findByNomeIgnoreCase(String nome);
}
