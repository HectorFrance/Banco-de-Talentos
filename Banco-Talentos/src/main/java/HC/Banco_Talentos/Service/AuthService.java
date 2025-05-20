package HC.Banco_Talentos.Service;

import HC.Banco_Talentos.DTO.AuthResponse;
import HC.Banco_Talentos.DTO.LoginRequest;
import HC.Banco_Talentos.DTO.RefreshRequest;
import HC.Banco_Talentos.Entity.Usuario;
import HC.Banco_Talentos.Repository.UsuarioRepository;
import HC.Banco_Talentos.Security.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private  final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse autenticar(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String accessToken = jwtUtil.gerarAccessToken(usuario.getEmail());
        String refreshToken = jwtUtil.gerarRefreshToken(usuario.getEmail());

        usuario.setRefreshToken(refreshToken);
        usuarioRepository.save(usuario);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse renovarToken(String refreshToken) {
        if (!jwtUtil.validarToken(refreshToken)) {
            throw new RuntimeException("Refresh token inválido ou expirado");
        }

        String email = jwtUtil.getEmailToken(refreshToken);

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (!refreshToken.equals(usuario.getRefreshToken())) {
            throw new RuntimeException("Refresh token não corresponde ao usuário");
        }

        String novoAccessToken = jwtUtil.gerarAccessToken(email);

        return new AuthResponse(novoAccessToken, refreshToken);
    }
}
