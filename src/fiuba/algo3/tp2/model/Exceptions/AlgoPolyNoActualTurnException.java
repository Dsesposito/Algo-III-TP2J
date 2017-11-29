package fiuba.algo3.tp2.model.Exceptions;

public class AlgoPolyNoActualTurnException extends RuntimeException {

    public AlgoPolyNoActualTurnException(String message) {
        super (message);
    }

    public AlgoPolyNoActualTurnException(Throwable cause) {
        super (cause);
    }

    public AlgoPolyNoActualTurnException(String message, Throwable cause) {
        super (message, cause);
    }
}