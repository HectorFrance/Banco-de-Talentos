package HC.Banco_Talentos.Application.Service;

import HC.Banco_Talentos.Interface.DTO.Request.CargoRequestDTO;
import HC.Banco_Talentos.Interface.DTO.Mapper.CargoMapper;
import HC.Banco_Talentos.Interface.DTO.Response.CargoResponseDTO;
import HC.Banco_Talentos.Domain.Entity.Cargo;
import HC.Banco_Talentos.Domain.Enum.Situacao;
import HC.Banco_Talentos.Shared.Exceptions.RegistroDuplicadoException;
import HC.Banco_Talentos.Infrastructure.Repository.CargoRepository;
import HC.Banco_Talentos.Shared.Utils.ControllerUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;

    public List<Cargo> getAll() {
        return cargoRepository.findAll();
    }

    public Cargo getById(Long id) {
        return cargoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cargo não Encontrado"));
    }

    public Cargo create(CargoRequestDTO cargoRequestDTO) {
        Cargo cargo = CargoMapper.INSTANCE.toEntity(cargoRequestDTO);
        cargo.setSituacao(Situacao.ATIVO);
        cargo.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        try {
          return cargoRepository.save(cargo);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
                String constraintName = constraintViolation.getConstraintName();

                if ("cargos.uk_profissao_senioridade".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Cargo já cadastrado.");
                }
            }
            throw new RuntimeException("Erro ao salvar Cargo: " + e.getMessage(), e);
        }
    }

    public Cargo findOrCreate(CargoRequestDTO cargoRequestDTO) {
        Optional<Cargo> cargoOptional = cargoRepository.findBySenioridadeAndProfissaoId(cargoRequestDTO.getSenioridade(), cargoRequestDTO.getProfissao());
        return cargoOptional.orElseGet(() -> create(cargoRequestDTO));
    }
}
