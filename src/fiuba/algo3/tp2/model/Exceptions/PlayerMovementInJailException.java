package fiuba.algo3.tp2.model.Exceptions;

public class PlayerMovementInJailException extends RuntimeException{

    public PlayerMovementInJailException(String message) {
        super (message);
    }

    public PlayerMovementInJailException(Throwable cause) {
        super (cause);
    }

    public PlayerMovementInJailException(String message, Throwable cause) {
        super (message, cause);
    }

}
