package HC.Banco_Talentos.Controller;

import HC.Banco_Talentos.DTO.ProfissaoDTO;
import HC.Banco_Talentos.Entity.Profissao;
import HC.Banco_Talentos.Service.ProfissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissao")
@RequiredArgsConstructor
public class ProfissaoController {

    private final ProfissaoService profissaoService;

    @GetMapping
    public ResponseEntity<List<ProfissaoDTO>> listAll() {
        return ResponseEntity.ok(profissaoService.getAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProfissaoDTO> cadastrar(@RequestBody ProfissaoDTO profissaoDTO) {
        return ResponseEntity.ok(profissaoService.create(profissaoDTO));
    }

}
