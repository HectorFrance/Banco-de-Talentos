package HC.Banco_Talentos.Controller;

import HC.Banco_Talentos.DTO.AuthResponse;
import HC.Banco_Talentos.DTO.LoginRequest;
import HC.Banco_Talentos.DTO.RefreshRequest;
import HC.Banco_Talentos.Service.AuthService;
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
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.autenticar(request);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(authService.renovarToken(request.getRefreshToken()));
    }
}
