package fiuba.algo3.tp2.model.Exceptions.PlayerExceptions;

public class PlayerInBankruptcyException extends PlayerException{

    public PlayerInBankruptcyException(String message) {
        super (message);
    }

    public PlayerInBankruptcyException(Throwable cause) {
        super (cause);
    }

    public PlayerInBankruptcyException(String message, Throwable cause) {
        super (message, cause);
    }

}
