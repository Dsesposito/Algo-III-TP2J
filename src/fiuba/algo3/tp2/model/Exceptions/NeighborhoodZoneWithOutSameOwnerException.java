package fiuba.algo3.tp2.model.Exceptions;

public class NeighborhoodZoneWithOutSameOwnerException extends RuntimeException{

    public NeighborhoodZoneWithOutSameOwnerException(String message) {
        super (message);
    }

    public NeighborhoodZoneWithOutSameOwnerException(Throwable cause) {
        super (cause);
    }

    public NeighborhoodZoneWithOutSameOwnerException(String message, Throwable cause) {
        super (message, cause);
    }

}
