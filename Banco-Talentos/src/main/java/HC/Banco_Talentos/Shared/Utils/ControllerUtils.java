package HC.Banco_Talentos.Shared.Utils;

import HC.Banco_Talentos.Domain.Entity.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ControllerUtils {

    public static Long getIdUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof Usuario) {
                return ((Usuario) principal).getId();
            }
        }
        return null;
    }

    public static Usuario getUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof Usuario) {
                return ((Usuario) principal);
            }
        }
        return null;
    }
}
