package HC.Banco_Talentos.Controller;


import HC.Banco_Talentos.DTO.Request.CandidatoRequestDTO;
import HC.Banco_Talentos.DTO.Response.CandidatoResponseDTO;
import HC.Banco_Talentos.Service.CandidatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidato")
@RequiredArgsConstructor
public class CandidatoController {

    private final CandidatoService candidatoService;

    @GetMapping
    public ResponseEntity<Page<CandidatoResponseDTO>> listAll( @RequestParam(name = "page", defaultValue = "0") int page,
                                                               @RequestParam(name = "limit", defaultValue = "10") int limit){
        return ResponseEntity.ok(candidatoService.getAll(PageRequest.of(page, limit)));
    }

    @PostMapping
    public ResponseEntity<CandidatoResponseDTO> cadastrar(@RequestBody CandidatoRequestDTO candidatoRequestDTO){
        return ResponseEntity.ok(candidatoService.create(candidatoRequestDTO));
    }
}
