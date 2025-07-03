package HC.Banco_Talentos.Interface.Controller;

import HC.Banco_Talentos.Interface.DTO.Request.CandidatoSkillRequestDTO;
import HC.Banco_Talentos.Interface.DTO.Response.CandidatoSkillResponseDTO;
import HC.Banco_Talentos.Application.Service.CandidatoSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidato-skills")
@RequiredArgsConstructor
public class CandidatoSkillController {

    private final CandidatoSkillService candidatoSkillService;

    @GetMapping
    public ResponseEntity<Page<CandidatoSkillResponseDTO>> listAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                  @RequestParam(name = "limit", defaultValue = "10") int limit){
        return ResponseEntity.ok(candidatoSkillService.getAll(PageRequest.of(page, limit)));
    }

    @PostMapping
    public ResponseEntity<CandidatoSkillResponseDTO> create(@RequestBody CandidatoSkillRequestDTO dto){
        return ResponseEntity.ok(candidatoSkillService.create(dto));
    }

}
