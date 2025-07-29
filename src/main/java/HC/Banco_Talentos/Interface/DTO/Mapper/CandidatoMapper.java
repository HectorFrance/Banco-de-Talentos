package HC.Banco_Talentos.Interface.DTO.Mapper;

import HC.Banco_Talentos.Interface.DTO.Request.CandidatoRequestDTO;
import HC.Banco_Talentos.Interface.DTO.Response.CandidatoResponseDTO;
import HC.Banco_Talentos.Domain.Entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = {
        CargoMapper.class,
        CidadeMapper.class,
        EstadoMapper.class,
        UsuarioMapper.class,
        CandidatoSkillMapper.class,
        ExperienciaMapper.class,
        AnotacaoMapper.class
})
public interface CandidatoMapper {

    CandidatoMapper INSTANCE = Mappers.getMapper(CandidatoMapper.class);

    @Mapping(source = "cidade.id", target = "cidade")
    @Mapping(source = "estado.id", target = "estado")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    @Mapping(source = "candidatoSkills", target = "candidatoSkills")
    @Mapping(source = "experiencias", target = "experiencias")
    @Mapping(source = "anotacoes", target = "anotacoes")
    CandidatoRequestDTO toDTO(Candidato candidato);

    @Mapping(target = "cidade", expression = "java(cidadeFromId(dto.getCidade()))")
    @Mapping(target = "estado", expression = "java(estadoFromId(dto.getEstado()))")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(target = "usuarioCriacao", expression = "java(usuarioFromId(dto.getUsuarioCriacao()))")
    @Mapping(source = "candidatoSkills", target = "candidatoSkills")
    @Mapping(source = "experiencias", target = "experiencias")
    @Mapping(source = "anotacoes", target = "anotacoes")
    Candidato toEntity(CandidatoRequestDTO dto);

    List<CandidatoRequestDTO> toDTO(List<Candidato> candidatos);
    List<Candidato> toEntity(List<CandidatoRequestDTO> dtoList);

    @Mapping(source = "cidade", target = "cidade")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "usuarioCriacao.id", target = "usuarioCriacao")
    @Mapping(source = "candidatoSkills", target = "candidatoSkills")
    @Mapping(source = "experiencias", target = "experiencias")
    @Mapping(source = "anotacoes", target = "anotacoes")
    CandidatoResponseDTO toResponseDTO(Candidato candidato);

    List<CandidatoResponseDTO> toResponseDTO(List<Candidato> candidatos);

    default Cidade cidadeFromId(Long id) {
        if (id == null) return null;
        Cidade c = new Cidade();
        c.setId(id);
        return c;
    }

    default Estado estadoFromId(Long id) {
        if (id == null) return null;
        Estado e = new Estado();
        e.setId(id);
        return e;
    }

    default Cargo cargoFromId(Long id) {
        if (id == null) return null;
        Cargo c = new Cargo();
        c.setId(id);
        return c;
    }

    default Usuario usuarioFromId(Long id) {
        if (id == null) return null;
        Usuario u = new Usuario();
        u.setId(id);
        return u;
    }

    default List<CandidatoSkill> candidatoSkillsFromIds(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            CandidatoSkill cs = new CandidatoSkill();
            cs.setId(id);
            return cs;
        }).collect(Collectors.toList());
    }

    default List<Experiencia> experienciasFromIds(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            Experiencia exp = new Experiencia();
            exp.setId(id);
            return exp;
        }).collect(Collectors.toList());
    }

    default List<Anotacao> anotacoesFromIds(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            Anotacao anot = new Anotacao();
            anot.setId(id);
            return anot;
        }).collect(Collectors.toList());
    }


    default List<Long> candidatoSkillIdsFromList(List<CandidatoSkill> list) {
        if (list == null) return null;
        return list.stream().map(CandidatoSkill::getId).collect(Collectors.toList());
    }

    default List<Long> experienciaIdsFromList(List<Experiencia> list) {
        if (list == null) return null;
        return list.stream().map(Experiencia::getId).collect(Collectors.toList());
    }

    default List<Long> anotacaoIdsFromList(List<Anotacao> list) {
        if (list == null) return null;
        return list.stream().map(Anotacao::getId).collect(Collectors.toList());
    }
}
