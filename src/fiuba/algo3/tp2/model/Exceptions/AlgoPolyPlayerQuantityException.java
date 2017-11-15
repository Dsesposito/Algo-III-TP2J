package fiuba.algo3.tp2.model.Exceptions;

public class AlgoPolyPlayerQuantityException extends RuntimeException {

    public AlgoPolyPlayerQuantityException(String message) {
        super (message);
    }

    public AlgoPolyPlayerQuantityException(Throwable cause) {
        super (cause);
    }

    public AlgoPolyPlayerQuantityException(String message, Throwable cause) {
        super (message, cause);
    }
}