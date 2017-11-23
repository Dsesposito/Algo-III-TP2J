package fiuba.algo3.tp2.model.Exceptions.NeighborhoodExceptions;

public class NeighborhoodZoneWithOutSameOwnerException extends NeighborhoodException{

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
