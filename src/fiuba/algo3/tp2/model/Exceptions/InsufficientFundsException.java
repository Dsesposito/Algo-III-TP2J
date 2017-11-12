package fiuba.algo3.tp2.model.Exceptions;

public class InsufficientFundsException extends RuntimeException{

    public InsufficientFundsException(String message) {
        super (message);
    }

    public InsufficientFundsException(Throwable cause) {
        super (cause);
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super (message, cause);
    }

}
