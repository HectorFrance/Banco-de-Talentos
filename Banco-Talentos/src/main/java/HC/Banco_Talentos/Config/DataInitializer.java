package HC.Banco_Talentos.Config;

import HC.Banco_Talentos.Entity.Usuario;
import HC.Banco_Talentos.Enum.Role;
import HC.Banco_Talentos.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initUsers(UsuarioRepository usuarioRepository) {
        return args -> {
            if (usuarioRepository.findByEmail("admin@email.com").isEmpty()) {
                Usuario admin = Usuario.builder()
                        .nome("Administrador")
                        .cpf("00000000000")
                        .email("admin@email.com")
                        .senha(passwordEncoder.encode("admin123"))
                        .role(Role.ROLE_ADMIN)
                        .build();

                usuarioRepository.save(admin);
                System.out.println("✅ Usuário ADMIN criado.");
            }

            if (usuarioRepository.findByEmail("user@email.com").isEmpty()) {
                Usuario user = Usuario.builder()
                        .nome("Usuário Comum")
                        .cpf("11111111111")
                        .email("user@email.com")
                        .senha(passwordEncoder.encode("user123"))
                        .role(Role.ROLE_USER)
                        .build();

                usuarioRepository.save(user);
                System.out.println("✅ Usuário USER criado.");
            }
        };
    }
}
