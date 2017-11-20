package fiuba.algo3.tp2.model.Exceptions;

public class PlayerActionInJailException extends RuntimeException{
    public PlayerActionInJailException(String message) {
        super (message);
    }

    public PlayerActionInJailException(Throwable cause) {
        super (cause);
    }

    public PlayerActionInJailException(String message, Throwable cause) {
        super (message, cause);
    }

}
