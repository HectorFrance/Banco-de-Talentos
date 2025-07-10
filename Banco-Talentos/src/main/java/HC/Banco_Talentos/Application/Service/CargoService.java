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
    private final ProfissaoService profissaoService;

    public List<CargoResponseDTO> getAll() {
        return CargoMapper.INSTANCE.toResponseDTO(cargoRepository.findAll());
    }

    public CargoResponseDTO getById(Long id) {
        return CargoMapper.INSTANCE.toResponseDTO(cargoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cargo não Encontrado")));
    }

    public CargoResponseDTO create(CargoRequestDTO cargoRequestDTO) {
        Cargo cargo = CargoMapper.INSTANCE.toEntity(cargoRequestDTO);
        cargo.setSituacao(Situacao.ATIVO);
        cargo.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        try {
            return CargoMapper.INSTANCE.toResponseDTO(cargoRepository.save(cargo));
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

    public CargoRequestDTO findOrCreate(CargoRequestDTO cargoRequestDTO) {
        Optional<Cargo> cargoOptional = cargoRepository.findBySenioridadeAndProfissaoId(cargoRequestDTO.getSenioridade(), cargoRequestDTO.getProfissao());
        if (cargoOptional.isPresent()) {
            return CargoMapper.INSTANCE.toDTO(cargoOptional.get());
        } else {
            CargoResponseDTO cargoNovo = create(cargoRequestDTO);
            CargoRequestDTO cargoRetorno = new CargoRequestDTO();
            cargoRetorno.setId(cargoNovo.getId());
            return cargoRetorno;
        }
    }
}
