package HC.Banco_Talentos.Interface.Controller;


import HC.Banco_Talentos.Interface.DTO.Mapper.CargoMapper;
import HC.Banco_Talentos.Interface.DTO.Request.CargoRequestDTO;
import HC.Banco_Talentos.Interface.DTO.Response.CargoResponseDTO;
import HC.Banco_Talentos.Application.Service.CargoService;
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
    public ResponseEntity<List<CargoResponseDTO>> listAll() {
        return ResponseEntity.ok(cargoService.getAll().stream().map(CargoMapper.INSTANCE :: toResponseDTO).toList());
    }

    @PostMapping
    public ResponseEntity<CargoResponseDTO> cadastrar(@RequestBody CargoRequestDTO cargoRequestDTO) {
        return ResponseEntity.ok(CargoMapper.INSTANCE.toResponseDTO(cargoService.create(cargoRequestDTO)));
    }
}
