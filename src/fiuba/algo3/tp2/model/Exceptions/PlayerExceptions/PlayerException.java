package fiuba.algo3.tp2.model.Exceptions.PlayerExceptions;

public class PlayerException extends RuntimeException {

    public PlayerException(String message) {
        super (message);
    }

    public PlayerException(Throwable cause) {
        super (cause);
    }

    public PlayerException(String message, Throwable cause) {
        super (message, cause);
    }
}
