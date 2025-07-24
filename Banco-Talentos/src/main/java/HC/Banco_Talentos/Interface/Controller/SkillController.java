package HC.Banco_Talentos.Interface.Controller;

import HC.Banco_Talentos.Interface.DTO.Mapper.SkillMapper;
import HC.Banco_Talentos.Interface.DTO.Request.SkillRequestDTO;
import HC.Banco_Talentos.Interface.DTO.Response.SkillResponseDTO;
import HC.Banco_Talentos.Application.Service.SkillService;
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
        return ResponseEntity.ok(skillService.getAll().stream().map(SkillMapper.INSTANCE :: toResponseDTO).toList());
    }

    @PostMapping
    public ResponseEntity<SkillResponseDTO> create(@RequestBody SkillRequestDTO dto) {
        return ResponseEntity.ok(SkillMapper.INSTANCE.toResponseDTO(skillService.create(dto)));
    }
}
