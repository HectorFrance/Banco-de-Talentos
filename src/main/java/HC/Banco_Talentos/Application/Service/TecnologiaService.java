package HC.Banco_Talentos.Application.Service;

import HC.Banco_Talentos.Interface.DTO.Mapper.TecnologiaMapper;
import HC.Banco_Talentos.Interface.DTO.TecnologiaDTO;
import HC.Banco_Talentos.Domain.Entity.Tecnologia;
import HC.Banco_Talentos.Domain.Enum.Situacao;
import HC.Banco_Talentos.Shared.Exceptions.RegistroDuplicadoException;
import HC.Banco_Talentos.Infrastructure.Repository.TecnologiaRepository;
import HC.Banco_Talentos.Shared.Utils.ControllerUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TecnologiaService {

    private final TecnologiaRepository tecnologiaRepository;

    public List<Tecnologia> getAll() {
        return tecnologiaRepository.findAll();
    }

    public Tecnologia findByid(Long id){
        return tecnologiaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tecnologia Não encontrada"));
    }

    public Tecnologia create(TecnologiaDTO dto) {

        Tecnologia tecnologia = TecnologiaMapper.INSTANCE.toEntity(dto);
        tecnologia.setSituacao(Situacao.ATIVO);
        tecnologia.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        try {
            return tecnologiaRepository.save(tecnologia);

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
                String constraintName = constraintViolation.getConstraintName();

                if ("tecnologias.tecnologia_unique".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Tecnologia já cadastrada.");
                }
            }
            throw new RuntimeException("Erro ao salvar tecnologia: " + e.getMessage(), e);
        }
    }

    public Tecnologia update(Long id, TecnologiaDTO dto) {

        Tecnologia tecnologia = tecnologiaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tecnologia Não encontrada"));
        tecnologia.setNome(dto.getNome());
        try {
            return tecnologiaRepository.save(tecnologia);

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
                String constraintName = constraintViolation.getConstraintName();

                if ("tecnologias.tecnologia_unique".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Tecnologia já cadastrada.");
                }
            }
            throw new RuntimeException("Erro ao salvar tecnologia: " + e.getMessage(), e);
        }
    }
}
