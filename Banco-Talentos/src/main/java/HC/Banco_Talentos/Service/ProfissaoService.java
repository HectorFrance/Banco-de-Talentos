package HC.Banco_Talentos.Service;

import HC.Banco_Talentos.DTO.Mapper.ProfissaoMapper;
import HC.Banco_Talentos.DTO.Mapper.TecnologiaMapper;
import HC.Banco_Talentos.DTO.ProfissaoDTO;
import HC.Banco_Talentos.Entity.Profissao;
import HC.Banco_Talentos.Enum.Situacao;
import HC.Banco_Talentos.Exceptions.RegistroDuplicadoException;
import HC.Banco_Talentos.Repository.ProfissaoRepository;
import HC.Banco_Talentos.Utils.ControllerUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfissaoService {

    private final ProfissaoRepository profissaoRepository;

    public List<ProfissaoDTO> getAll() {
        return profissaoRepository.findAll()
                .stream()
                .map(ProfissaoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProfissaoDTO findById(Long id) {
        return ProfissaoMapper.toDTO(profissaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Profissão não encontrada")));
    }

    public ProfissaoDTO create(ProfissaoDTO profissaoDTO) {

        Profissao profissao = ProfissaoMapper.toEntity(profissaoDTO);
        profissao.setSituacao(Situacao.ATIVO);
        profissao.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        try {
            return ProfissaoMapper.toDTO(profissaoRepository.save(profissao));

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
                String constraintName = constraintViolation.getConstraintName();

                if ("profissao_unique".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Profissão já cadastrada.");
                }
            }
            throw new RuntimeException("Erro ao salvar profissão: " + e.getMessage(), e);
        }
    }

    public ProfissaoDTO update (Long id, ProfissaoDTO profissaoDTO) {

        Profissao profissao = profissaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Profissão não encontrada"));
        profissao.setNome(profissaoDTO.getNome());

        try {
            return ProfissaoMapper.toDTO(profissaoRepository.save(profissao));

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
                String constraintName = constraintViolation.getConstraintName();

                if ("profissao_unique".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Profissão já cadastrada.");
                }
            }
            throw new RuntimeException("Erro ao salvar profissão: " + e.getMessage(), e);
        }
    }
}
