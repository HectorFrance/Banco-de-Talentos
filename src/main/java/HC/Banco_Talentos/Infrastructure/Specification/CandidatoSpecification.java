package HC.Banco_Talentos.Infrastructure.Specification;

import HC.Banco_Talentos.Domain.Entity.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CandidatoSpecification {

    public static Specification<Candidato> comSkillIds(List<Long> skillIds) {
        return (root, query, criteriaBuilder) -> {
            if (skillIds == null || skillIds.isEmpty()) {
                return criteriaBuilder.conjunction();
            }


            Join<Candidato, CandidatoSkill> joinCandidatoSkill = root.join("candidatoSkills", JoinType.INNER);
            Join<CandidatoSkill, Skill> joinSkill = joinCandidatoSkill.join("skill", JoinType.INNER);

            return joinSkill.get("id").in(skillIds);
        };
    }

    public static Specification<Candidato> comTecnologiaENivel(Long tecnologiaId, String nivel) {
        return (root, query, cb) -> {
            Join<Candidato, CandidatoSkill> joinCandidatoSkill = root.join("candidatoSkills", JoinType.INNER);
            Join<CandidatoSkill, Skill> joinSkill = joinCandidatoSkill.join("skill", JoinType.INNER);

            Predicate tecnologiaPredicate = cb.equal(joinSkill.get("tecnologia").get("id"), tecnologiaId);
            Predicate nivelPredicate = cb.equal(joinSkill.get("nivel"), nivel);

            return cb.and(tecnologiaPredicate, nivelPredicate);
        };
    }

    public static Specification<Candidato> comTecnologia(Long tecnologiaId) {
        return (root, query, cb) -> {
            if (tecnologiaId == null) {
                return cb.conjunction();
            }

            // Join: Candidato → CandidatoSkill → Skill
            Join<Candidato, CandidatoSkill> joinCandidatoSkill = root.join("candidatoSkills", JoinType.INNER);
            Join<CandidatoSkill, Skill> joinSkill = joinCandidatoSkill.join("skill", JoinType.INNER);

            return cb.equal(joinSkill.get("tecnologia").get("id"), tecnologiaId);
        };
    }

    public static Specification<Candidato> comCargoOuExperiencia(Long cargoId) {
        return (root, query, cb) -> {
            if (cargoId == null) {
                return cb.conjunction();
            }

            // Join com experiências
            Join<Candidato, Experiencia> joinExperiencias = root.join("experiencias", JoinType.LEFT);
            Join<Experiencia, Cargo> joinCargoExperiencia = joinExperiencias.join("cargo", JoinType.LEFT);

            // Filtro: candidato.cargo.id == cargoId
            Predicate cargoDireto = cb.equal(root.get("cargo").get("id"), cargoId);

            // Filtro: experiencia.cargo.id == cargoId
            Predicate cargoNaExperiencia = cb.equal(joinCargoExperiencia.get("id"), cargoId);

            // Retorna candidatos com cargo direto OU em alguma experiência
            return cb.or(cargoDireto, cargoNaExperiencia);
        };
    }
}
