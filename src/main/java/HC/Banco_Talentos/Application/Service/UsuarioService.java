package HC.Banco_Talentos.Application.Service;

import HC.Banco_Talentos.Interface.DTO.Response.AuthResponse;
import HC.Banco_Talentos.Interface.DTO.Request.LoginRequest;
import HC.Banco_Talentos.Interface.DTO.Mapper.UsuarioMapper;
import HC.Banco_Talentos.Interface.DTO.UsuarioDTO;
import HC.Banco_Talentos.Domain.Entity.Usuario;
import HC.Banco_Talentos.Domain.Enum.Role;
import HC.Banco_Talentos.Domain.Enum.Situacao;
import HC.Banco_Talentos.Shared.Exceptions.CpfInvalidoException;
import HC.Banco_Talentos.Shared.Exceptions.EmailInvalidoException;
import HC.Banco_Talentos.Shared.Exceptions.UsuarioDuplicadoException;
import HC.Banco_Talentos.Infrastructure.Repository.UsuarioRepository;
import HC.Banco_Talentos.Infrastructure.Security.JwtUtil;
import HC.Banco_Talentos.Shared.Utils.DocumentoUtils;
import HC.Banco_Talentos.Shared.Utils.EmailUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioService {

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

        return new AuthResponse(usuario.getNome(), usuario.getEmail(),accessToken, refreshToken);
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

        return new AuthResponse(usuario.getNome(), usuario.getEmail(), novoAccessToken, refreshToken);
    }

    public AuthResponse cadastrar(UsuarioDTO usuarioDTO) {
        String encodedPassword = passwordEncoder.encode(usuarioDTO.getSenha());
        Usuario usuario = UsuarioMapper.INSTANCE.toEntity(usuarioDTO);
        usuario.setSenha(encodedPassword);
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setSituacao(Situacao.ATIVO);
        usuario.setRole(Role.ROLE_USER);

        if (!DocumentoUtils.cpfValido(usuario.getCpf()))
            throw new CpfInvalidoException("CPF inválido");

        if (!EmailUtils.emailValido(usuario.getEmail()))
            throw new EmailInvalidoException("E-mail inválido");

        try {
            usuarioRepository.save(usuario);
            LoginRequest loginRequest = new LoginRequest(usuarioDTO.getEmail(), usuarioDTO.getSenha());
            return autenticar(loginRequest);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            while (cause != null) {
                String message = cause.getMessage();
                if (message != null) {
                    if (message.contains("usuario_email_unique")) {
                        throw new UsuarioDuplicadoException("E-mail já Cadastrado");
                    } else if (message.contains("usuario_cpf_unique")) {
                        throw new UsuarioDuplicadoException("CPF já Cadastrado");
                    }
                }
                cause = cause.getCause();
            }
            throw new RuntimeException("Erro ao cadastrar usuário");
        }
    }
}
