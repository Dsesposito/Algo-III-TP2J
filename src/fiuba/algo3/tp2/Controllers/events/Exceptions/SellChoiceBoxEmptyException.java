package fiuba.algo3.tp2.Controllers.events.Exceptions;

public class SellChoiceBoxEmptyException extends RuntimeException{

    public SellChoiceBoxEmptyException(String message) {
        super (message);
    }

    public SellChoiceBoxEmptyException(Throwable cause) {
        super (cause);
    }

    public SellChoiceBoxEmptyException(String message, Throwable cause) {
        super (message, cause);
    }

}
