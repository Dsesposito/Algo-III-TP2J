package fiuba.algo3.tp2.model.Exceptions;

public class NeighborhoodWithHotelAlreadyBuiltException extends RuntimeException{

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
