package HC.Banco_Talentos.Repository;

import HC.Banco_Talentos.Entity.Skill;
import HC.Banco_Talentos.Entity.Tecnologia;
import HC.Banco_Talentos.Enum.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Optional<Skill> findByTecnologiaAndNivel(Tecnologia tecnologia, Nivel nivel);
}
