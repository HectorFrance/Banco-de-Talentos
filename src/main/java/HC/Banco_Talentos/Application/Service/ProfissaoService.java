package HC.Banco_Talentos.Application.Service;

import HC.Banco_Talentos.Interface.DTO.Mapper.ProfissaoMapper;
import HC.Banco_Talentos.Interface.DTO.ProfissaoDTO;
import HC.Banco_Talentos.Domain.Entity.Profissao;
import HC.Banco_Talentos.Domain.Enum.Situacao;
import HC.Banco_Talentos.Shared.Exceptions.RegistroDuplicadoException;
import HC.Banco_Talentos.Infrastructure.Repository.ProfissaoRepository;
import HC.Banco_Talentos.Shared.Utils.ControllerUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfissaoService {

    private final ProfissaoRepository profissaoRepository;

    public List<Profissao> getAll() {
        return profissaoRepository.findAll();
    }

    public Profissao findById(Long id) {
        return profissaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Profissão não encontrada"));
    }

    public Profissao create(ProfissaoDTO profissaoDTO) {

        Profissao profissao = ProfissaoMapper.INSTANCE.toEntity(profissaoDTO);
        profissao.setSituacao(Situacao.ATIVO);
        profissao.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        try {
            return profissaoRepository.save(profissao);

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
                String constraintName = constraintViolation.getConstraintName();

                if ("profissoes.profissao_unique".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Profissão já cadastrada.");
                }
            }
            throw new RuntimeException("Erro ao salvar profissão: " + e.getMessage(), e);
        }
    }

    public Profissao update (Long id, ProfissaoDTO profissaoDTO) {

        Profissao profissao = profissaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Profissão não encontrada"));
        profissao.setNome(profissaoDTO.getNome());

        try {
            return profissaoRepository.save(profissao);

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
                String constraintName = constraintViolation.getConstraintName();

                if ("profissoes.profissao_unique".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Profissão já cadastrada.");
                }
            }
            throw new RuntimeException("Erro ao salvar profissão: " + e.getMessage(), e);
        }
    }
}
