package fiuba.algo3.tp2.model.Exceptions;

public class NeighborhoodFullHousesException extends RuntimeException{

    public NeighborhoodFullHousesException(String message) {
        super (message);
    }

    public NeighborhoodFullHousesException(Throwable cause) {
        super (cause);
    }

    public NeighborhoodFullHousesException(String message, Throwable cause) {
        super (message, cause);
    }

}
