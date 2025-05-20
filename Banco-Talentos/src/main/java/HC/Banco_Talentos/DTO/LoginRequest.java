package HC.Banco_Talentos.DTO;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String senha;
}
