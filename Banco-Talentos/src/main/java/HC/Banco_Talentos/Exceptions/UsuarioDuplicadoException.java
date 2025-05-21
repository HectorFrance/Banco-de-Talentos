package HC.Banco_Talentos.Exceptions;

public class UsuarioDuplicadoException extends RuntimeException {
    public UsuarioDuplicadoException(String message) {
        super(message);
    }
}
