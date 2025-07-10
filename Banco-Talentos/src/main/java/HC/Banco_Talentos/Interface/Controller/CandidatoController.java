package HC.Banco_Talentos.Interface.Controller;


import HC.Banco_Talentos.Interface.DTO.Request.CandidatoRequestDTO;
import HC.Banco_Talentos.Interface.DTO.Response.CandidatoResponseDTO;
import HC.Banco_Talentos.Application.Service.CandidatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/tecnologia/{tecnologiaId:\\d+}")
    public ResponseEntity<Page<CandidatoResponseDTO>> getByTecnologia( @RequestParam(name = "page", defaultValue = "0") int page,
                                                                       @RequestParam(name = "limit", defaultValue = "10") int limit,
                                                                       @PathVariable Long tecnologiaId){
        return ResponseEntity.ok(candidatoService.getByTecnologia(PageRequest.of(page, limit), tecnologiaId));
    }

    @PostMapping
    public ResponseEntity<CandidatoResponseDTO> cadastrar( @RequestPart("candidato") CandidatoRequestDTO candidatoRequestDTO,
                                                           @RequestPart("curriculo") MultipartFile curriculo){
        return ResponseEntity.ok(candidatoService.create(candidatoRequestDTO, curriculo));
    }
}
