package fiuba.algo3.tp2.model.Exceptions;

public class BoardPlayerQuantityException extends RuntimeException {

    public BoardPlayerQuantityException(String message) {
        super (message);
    }

    public BoardPlayerQuantityException(Throwable cause) {
        super (cause);
    }

    public BoardPlayerQuantityException(String message, Throwable cause) {
        super (message, cause);
    }
}
