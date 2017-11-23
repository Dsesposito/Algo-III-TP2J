package fiuba.algo3.tp2.model.Exceptions.NeighborhoodExceptions;

public class NeighborhoodException extends RuntimeException {

    public NeighborhoodException(String message) {
        super (message);
    }

    public NeighborhoodException(Throwable cause) {
        super (cause);
    }

    public NeighborhoodException(String message, Throwable cause) {
        super (message, cause);
    }
}
