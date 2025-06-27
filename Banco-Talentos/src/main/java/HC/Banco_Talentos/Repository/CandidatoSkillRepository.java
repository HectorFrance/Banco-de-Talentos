package HC.Banco_Talentos.Repository;

import HC.Banco_Talentos.Entity.CandidatoSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatoSkillRepository extends JpaRepository<CandidatoSkill, Long> {

    List<CandidatoSkill> findByCandidatoId(Long candidatoId);
}
