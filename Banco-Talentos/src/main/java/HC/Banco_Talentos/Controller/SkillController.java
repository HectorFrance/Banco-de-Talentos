package HC.Banco_Talentos.Controller;

import HC.Banco_Talentos.DTO.SkillDTO;
import HC.Banco_Talentos.Service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<List<SkillDTO>> listAll() {
        return ResponseEntity.ok(skillService.getAll());
    }

    @PostMapping
    public ResponseEntity<SkillDTO> create(@RequestBody SkillDTO dto) {
        return ResponseEntity.ok(skillService.create(dto));
    }
}
