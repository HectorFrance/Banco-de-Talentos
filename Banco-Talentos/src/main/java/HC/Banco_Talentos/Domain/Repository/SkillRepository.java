package HC.Banco_Talentos.Domain.Repository;

import HC.Banco_Talentos.Domain.Entity.Skill;
import HC.Banco_Talentos.Domain.Enum.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Optional<Skill> findByTecnologiaIdAndNivel(Long tecnologia, Nivel nivel);
}
