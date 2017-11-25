package fiuba.algo3.tp2.model.Exceptions.PlayerExceptions;

public class PlayerNotAbleToPayBailException extends PlayerException{

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
