package fiuba.algo3.tp2.model.Exceptions;

public class NeighborhoodWithOutAllHousesBuiltException extends RuntimeException{

    public NeighborhoodWithOutAllHousesBuiltException(String message) {
        super (message);
    }

    public NeighborhoodWithOutAllHousesBuiltException(Throwable cause) {
        super (cause);
    }

    public NeighborhoodWithOutAllHousesBuiltException(String message, Throwable cause) {
        super (message, cause);
    }

}
