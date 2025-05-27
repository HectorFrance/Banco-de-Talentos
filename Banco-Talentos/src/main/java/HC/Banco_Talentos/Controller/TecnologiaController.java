package HC.Banco_Talentos.Controller;

import HC.Banco_Talentos.DTO.AuthResponse;
import HC.Banco_Talentos.DTO.TecnologiaDTO;
import HC.Banco_Talentos.DTO.UsuarioDTO;
import HC.Banco_Talentos.Service.TecnologiaService;
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
        return ResponseEntity.ok(tecnologiaService.getAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TecnologiaDTO> cadastrar(@RequestBody TecnologiaDTO request) {
        return ResponseEntity.ok(tecnologiaService.save(request));
    }
}
