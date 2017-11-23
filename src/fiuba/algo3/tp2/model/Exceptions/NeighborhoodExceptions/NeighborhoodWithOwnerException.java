package fiuba.algo3.tp2.model.Exceptions.NeighborhoodExceptions;

public class NeighborhoodWithOwnerException extends NeighborhoodException{

    public NeighborhoodWithOwnerException(String message) {
        super (message);
    }

    public NeighborhoodWithOwnerException(Throwable cause) {
        super (cause);
    }

    public NeighborhoodWithOwnerException(String message, Throwable cause) {
        super (message, cause);
    }

}
