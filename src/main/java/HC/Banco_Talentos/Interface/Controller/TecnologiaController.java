package HC.Banco_Talentos.Interface.Controller;

import HC.Banco_Talentos.Interface.DTO.Mapper.TecnologiaMapper;
import HC.Banco_Talentos.Interface.DTO.TecnologiaDTO;
import HC.Banco_Talentos.Application.Service.TecnologiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnologia")
@RequiredArgsConstructor
public class TecnologiaController {

    private final TecnologiaService tecnologiaService;

    @GetMapping
    public ResponseEntity<List<TecnologiaDTO>> listAll(){
        return ResponseEntity.ok(tecnologiaService.getAll().stream().map(TecnologiaMapper.INSTANCE :: toDTO).toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TecnologiaDTO> cadastrar(@RequestBody TecnologiaDTO request) {
        return ResponseEntity.ok(TecnologiaMapper.INSTANCE.toDTO(tecnologiaService.create(request)));
    }
}
