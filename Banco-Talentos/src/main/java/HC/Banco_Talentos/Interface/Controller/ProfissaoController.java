package HC.Banco_Talentos.Interface.Controller;

import HC.Banco_Talentos.Interface.DTO.Mapper.ProfissaoMapper;
import HC.Banco_Talentos.Interface.DTO.ProfissaoDTO;
import HC.Banco_Talentos.Application.Service.ProfissaoService;
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
        return ResponseEntity.ok(profissaoService.getAll().stream().map(ProfissaoMapper.INSTANCE :: toDTO).toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProfissaoDTO> cadastrar(@RequestBody ProfissaoDTO profissaoDTO) {
        return ResponseEntity.ok(ProfissaoMapper.INSTANCE.toDTO(profissaoService.create(profissaoDTO)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProfissaoDTO> alterar(@PathVariable Long id, @RequestBody ProfissaoDTO profissaoDTO) {
        return ResponseEntity.ok(ProfissaoMapper.INSTANCE.toDTO(profissaoService.update(id, profissaoDTO)));
    }

}
