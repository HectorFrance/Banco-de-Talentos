package HC.Banco_Talentos.Repository;

import HC.Banco_Talentos.Entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
