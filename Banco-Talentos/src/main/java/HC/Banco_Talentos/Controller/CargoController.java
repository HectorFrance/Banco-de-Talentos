package HC.Banco_Talentos.Controller;

import HC.Banco_Talentos.DTO.Request.CargoRequestDTO;
import HC.Banco_Talentos.DTO.Response.CargoResponseDTO;
import HC.Banco_Talentos.Service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargo")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    @GetMapping
    public ResponseEntity<List<CargoResponseDTO>> listAll(){
        return ResponseEntity.ok(cargoService.getAll());
    }

    @PostMapping
    public ResponseEntity<CargoResponseDTO> cadastrar(@RequestBody CargoRequestDTO cargoRequestDTO){
        return ResponseEntity.ok(cargoService.create(cargoRequestDTO));
    }
}
