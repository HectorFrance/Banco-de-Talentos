package HC.Banco_Talentos.Service;

import HC.Banco_Talentos.Entity.Estado;
import HC.Banco_Talentos.Repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    private List<Estado> getAll(){
        return estadoRepository.findAll();
    }

    private Optional<Estado> findById(Long id){
        return  estadoRepository.findById(id);
    }
}
