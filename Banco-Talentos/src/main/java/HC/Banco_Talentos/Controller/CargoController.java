package HC.Banco_Talentos.Controller;

import HC.Banco_Talentos.DTO.CargoDTO;
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
    public ResponseEntity<List<CargoDTO>> listAll(){
        return ResponseEntity.ok(cargoService.getAll());
    }

    @PostMapping
    public ResponseEntity<CargoDTO> cadastrar(@RequestBody CargoDTO cargoDTO){
        return ResponseEntity.ok(cargoService.create(cargoDTO));
    }
}
