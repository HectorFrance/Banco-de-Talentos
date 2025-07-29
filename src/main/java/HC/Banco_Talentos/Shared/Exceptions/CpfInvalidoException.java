package HC.Banco_Talentos.Shared.Exceptions;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(String message) {
        super(message);
    }
}
