package HC.Banco_Talentos.Entity;

import HC.Banco_Talentos.Enum.Role;
import HC.Banco_Talentos.Enum.Situacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USUARIOS",
        uniqueConstraints = {
                @UniqueConstraint(name = "usuario_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "usuario_cpf_unique", columnNames = "cpf")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private  String senha;

    private LocalDateTime dataCadastro;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(length = 1000)
    private String refreshToken;

    @Override
    public Collection<? extends Role> getAuthorities() {
        return List.of(role);
    }
    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
