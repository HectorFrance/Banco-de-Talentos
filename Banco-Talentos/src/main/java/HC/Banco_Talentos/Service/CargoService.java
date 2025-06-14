package HC.Banco_Talentos.Service;

import HC.Banco_Talentos.DTO.CargoDTO;
import HC.Banco_Talentos.DTO.Mapper.CargoMapper;
import HC.Banco_Talentos.Entity.Cargo;
import HC.Banco_Talentos.Enum.Situacao;
import HC.Banco_Talentos.Exceptions.RegistroDuplicadoException;
import HC.Banco_Talentos.Repository.CargoRepository;
import HC.Banco_Talentos.Utils.ControllerUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;
    private final ProfissaoService profissaoService;

    public List<CargoDTO> getAll() {
        return CargoMapper.INSTANCE.toDTO(cargoRepository.findAll());
    }

    public CargoDTO getById(Long id) {
        return CargoMapper.INSTANCE.toDTO(cargoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cargo não Encontrado")));
    }

    public CargoDTO create(CargoDTO cargoDTO){
        Cargo cargo = CargoMapper.INSTANCE.toEntity(cargoDTO);
        cargo.setSituacao(Situacao.ATIVO);
        cargo.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        try{
            return CargoMapper.INSTANCE.toDTO(cargoRepository.save(cargo));
        }catch (DataIntegrityViolationException e){
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation){
                String constraintName = constraintViolation.getConstraintName();

                if ("cargos.uk_profissao_senioridade".equalsIgnoreCase(constraintName)) {
                    throw new RegistroDuplicadoException("Cargo já cadastrado.");
                }
            }
            throw new RuntimeException("Erro ao salvar Cargo: " + e.getMessage(), e);
        }
    }
}
