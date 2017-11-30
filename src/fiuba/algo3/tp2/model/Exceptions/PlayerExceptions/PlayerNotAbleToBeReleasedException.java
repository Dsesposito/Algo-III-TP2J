package fiuba.algo3.tp2.model.Exceptions.PlayerExceptions;

public class PlayerNotAbleToBeReleasedException extends PlayerException{

    public PlayerNotAbleToBeReleasedException(String message) {
        super (message);
    }

    public PlayerNotAbleToBeReleasedException(Throwable cause) {
        super (cause);
    }

    public PlayerNotAbleToBeReleasedException(String message, Throwable cause) {
        super (message, cause);
    }

}
