package fiuba.algo3.tp2.model.Exceptions.NeighborhoodExceptions;

public class NeighborhoodWithOutOwnerException extends NeighborhoodException{

    public NeighborhoodWithOutOwnerException(String message) {
        super (message);
    }

    public NeighborhoodWithOutOwnerException(Throwable cause) {
        super (cause);
    }

    public NeighborhoodWithOutOwnerException(String message, Throwable cause) {
        super (message, cause);
    }

}
