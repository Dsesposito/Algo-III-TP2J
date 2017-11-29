package fiuba.algo3.tp2.view.events.Exceptions;

public class BuildChoiceBoxEmptyException extends RuntimeException{

    public BuildChoiceBoxEmptyException(String message) {
        super (message);
    }

    public BuildChoiceBoxEmptyException(Throwable cause) {
        super (cause);
    }

    public BuildChoiceBoxEmptyException(String message, Throwable cause) {
        super (message, cause);
    }

}
