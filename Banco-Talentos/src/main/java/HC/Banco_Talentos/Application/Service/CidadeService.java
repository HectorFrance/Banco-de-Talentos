package HC.Banco_Talentos.Application.Service;

import HC.Banco_Talentos.Domain.Entity.Cidade;
import HC.Banco_Talentos.Infrastructure.Repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public List<Cidade> getAll(){
        return cidadeRepository.findAll();
    }

    public List<Cidade> findByEstadoId(Long id){
        return cidadeRepository.findByEstadoId(id);
    }

    public Optional<Cidade> findById(Long id){
        return cidadeRepository.findById(id);
    }
}
