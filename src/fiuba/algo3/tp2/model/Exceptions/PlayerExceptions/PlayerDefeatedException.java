package fiuba.algo3.tp2.model.Exceptions.PlayerExceptions;

public class PlayerDefeatedException extends PlayerException{

    public PlayerDefeatedException(String message) {
        super (message);
    }

    public PlayerDefeatedException(Throwable cause) {
        super (cause);
    }

    public PlayerDefeatedException(String message, Throwable cause) {
        super (message, cause);
    }

}
