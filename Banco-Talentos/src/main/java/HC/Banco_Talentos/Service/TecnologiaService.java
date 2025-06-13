package HC.Banco_Talentos.Service;

import HC.Banco_Talentos.DTO.Mapper.TecnologiaMapper;
import HC.Banco_Talentos.DTO.TecnologiaDTO;
import HC.Banco_Talentos.Entity.Tecnologia;
import HC.Banco_Talentos.Enum.Situacao;
import HC.Banco_Talentos.Exceptions.RegistroDuplicadoException;
import HC.Banco_Talentos.Repository.TecnologiaRepository;
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
public class TecnologiaService {

    private final TecnologiaRepository tecnologiaRepository;

    public List<TecnologiaDTO> getAll() {
        return tecnologiaRepository.findAll()
                .stream()
                .map(TecnologiaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TecnologiaDTO findByid(Long id){
        return TecnologiaMapper.toDTO(tecnologiaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tecnologia Não encontrada")));
    }

    public TecnologiaDTO create(TecnologiaDTO dto) {

        Tecnologia tecnologia = TecnologiaMapper.toEntity(dto);
        tecnologia.setSituacao(Situacao.ATIVO);
        tecnologia.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        try {
            return TecnologiaMapper.toDTO(tecnologiaRepository.save(tecnologia));

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
                String constraintName = constraintViolation.getConstraintName();

                if ("tecnologia_unique".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Tecnologia já cadastrada.");
                }
            }
            throw new RuntimeException("Erro ao salvar tecnologia: " + e.getMessage(), e);
        }
    }

    public TecnologiaDTO update(Long id, TecnologiaDTO dto) {

        Tecnologia tecnologia = tecnologiaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tecnologia Não encontrada"));
        tecnologia.setNome(dto.getNome());
        try {
            return TecnologiaMapper.toDTO(tecnologiaRepository.save(tecnologia));

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
                String constraintName = constraintViolation.getConstraintName();

                if ("tecnologia_unique".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Tecnologia já cadastrada.");
                }
            }
            throw new RuntimeException("Erro ao salvar tecnologia: " + e.getMessage(), e);
        }
    }
}
