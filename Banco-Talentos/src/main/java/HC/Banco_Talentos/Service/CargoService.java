package HC.Banco_Talentos.Service;

import HC.Banco_Talentos.Repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;
    private final ProfissaoService profissaoService;


}
