package HC.Banco_Talentos.Domain.Repository;

import HC.Banco_Talentos.Domain.Entity.CandidatoSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatoSkillRepository extends JpaRepository<CandidatoSkill, Long> {

    List<CandidatoSkill> findByCandidatoId(Long candidatoId);
}
