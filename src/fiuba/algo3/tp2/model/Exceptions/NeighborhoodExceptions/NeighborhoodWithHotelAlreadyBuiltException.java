package fiuba.algo3.tp2.model.Exceptions.NeighborhoodExceptions;

public class NeighborhoodWithHotelAlreadyBuiltException extends NeighborhoodException{

    public NeighborhoodWithHotelAlreadyBuiltException(String message) {
        super (message);
    }

    public NeighborhoodWithHotelAlreadyBuiltException(Throwable cause) {
        super (cause);
    }

    public NeighborhoodWithHotelAlreadyBuiltException(String message, Throwable cause) {
        super (message, cause);
    }

}
