package HC.Banco_Talentos.Service;

import HC.Banco_Talentos.DTO.Mapper.TecnologiaMapper;
import HC.Banco_Talentos.DTO.TecnologiaDTO;
import HC.Banco_Talentos.Entity.Tecnologia;
import HC.Banco_Talentos.Enum.Situacao;
import HC.Banco_Talentos.Exceptions.TecnologiaDuplicadaException;
import HC.Banco_Talentos.Repository.TecnologiaRepository;
import HC.Banco_Talentos.Utils.ControllerUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TecnologiaService {

    private final TecnologiaRepository tecnologiaRepository;

    public List<TecnologiaDTO> getAll(){
        return tecnologiaRepository.findAll()
                .stream()
                .map(TecnologiaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TecnologiaDTO save(TecnologiaDTO dto){

        Optional<Tecnologia> tecnologiaOptional = tecnologiaRepository.findByNomeIgnoreCase(dto.getNome().trim());
        if(tecnologiaOptional.isPresent())
            throw new TecnologiaDuplicadaException("Tecnologia já cadastrada");


        Tecnologia tecnologia = TecnologiaMapper.toEntity(dto);
        tecnologia.setDataCriacao(LocalDateTime.now());
        tecnologia.setSituacao(Situacao.ATIVO);
        tecnologia.setUsuarioCriacao(ControllerUtils.getUsuarioLogado());

        return TecnologiaMapper.toDTO(tecnologiaRepository.save(tecnologia));
    }
}
