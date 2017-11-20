package fiuba.algo3.tp2.model.Exceptions;

public class NeighborhoodWithOutOwnerException extends RuntimeException{

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
