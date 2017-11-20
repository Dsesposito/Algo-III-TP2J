package fiuba.algo3.tp2.model.Exceptions;

public class PlayerNotAbleToPayBailException extends RuntimeException{

    public PlayerNotAbleToPayBailException(String message) {
        super (message);
    }

    public PlayerNotAbleToPayBailException(Throwable cause) {
        super (cause);
    }

    public PlayerNotAbleToPayBailException(String message, Throwable cause) {
        super (message, cause);
    }

}
