package HC.Banco_Talentos.Interface.Controller;

import HC.Banco_Talentos.Interface.DTO.Response.AuthResponse;
import HC.Banco_Talentos.Interface.DTO.Request.LoginRequest;
import HC.Banco_Talentos.Interface.DTO.Request.RefreshRequest;
import HC.Banco_Talentos.Interface.DTO.UsuarioDTO;
import HC.Banco_Talentos.Application.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return usuarioService.autenticar(request);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(usuarioService.renovarToken(request.getRefreshToken()));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<AuthResponse> cadastrar(@RequestBody UsuarioDTO request) {
        return ResponseEntity.ok(usuarioService.cadastrar(request));
    }
}
