package HC.Banco_Talentos.Controller;

import HC.Banco_Talentos.DTO.Request.SkillRequestDTO;
import HC.Banco_Talentos.DTO.SkillResponseDTO;
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
    public ResponseEntity<List<SkillResponseDTO>> listAll() {
        return ResponseEntity.ok(skillService.getAll());
    }

    @PostMapping
    public ResponseEntity<SkillResponseDTO> create(@RequestBody SkillRequestDTO dto) {
        return ResponseEntity.ok(skillService.create(dto));
    }
}
