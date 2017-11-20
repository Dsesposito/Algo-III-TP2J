package fiuba.algo3.tp2.model.Exceptions;

public class CellNotFoundException extends RuntimeException{

    public CellNotFoundException(String message) {
        super (message);
    }

    public CellNotFoundException(Throwable cause) {
        super (cause);
    }

    public CellNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }

}
