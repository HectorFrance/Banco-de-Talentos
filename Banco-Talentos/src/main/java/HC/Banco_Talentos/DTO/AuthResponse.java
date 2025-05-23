package HC.Banco_Talentos.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String nome;
    private String email;
    private String token;
    private String refreshToken;
}
