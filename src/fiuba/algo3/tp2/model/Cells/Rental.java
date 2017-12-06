package fiuba.algo3.tp2.model.Cells;
import fiuba.algo3.tp2.model.Money;

import java.util.List;

public class Rental {
    private Money rentalPriceWithoutBuildings;
    private Money rentalPrice;
    private  List<Money> rentalPricesDueHouses;
    private  List<Money> rentalPricesDueHotels;

    private Long numberOfBuiltHouses;
    private Long numberOfBuiltHotels;


    public Rental(Money rentalPriceWithoutBuildings, List<Money> rentalPricesDueHouses, List<Money> rentalPricesDueHotels){
        this.rentalPricesDueHouses = rentalPricesDueHouses;
        this.rentalPricesDueHotels = rentalPricesDueHotels;
        this.rentalPriceWithoutBuildings = rentalPriceWithoutBuildings;
        this.numberOfBuiltHotels = 0L;
        this.numberOfBuiltHouses = 0L;
        this.rentalPrice = rentalPriceWithoutBuildings;
    }

    public void incrementBuiltHouses(){
        numberOfBuiltHouses++;
        rentalPrice = rentalPricesDueHouses.get(Math.toIntExact(this.getNumberOfBuiltHouses()) - 1);
    }

    public void incrementBuiltHotels(){
        numberOfBuiltHotels++;
        rentalPrice = rentalPricesDueHotels.get(Math.toIntExact(this.getNumberOfBuiltHotels()) - 1);
    }

    public void clearBuiltHouses(){
        numberOfBuiltHouses = 0L;
        rentalPrice = rentalPriceWithoutBuildings;
    }

    public void clearBuiltHousesAndHotels(){
        numberOfBuiltHouses = 0L;
        numberOfBuiltHotels = 0L;
        rentalPrice = rentalPriceWithoutBuildings;
    }

    public Money getRentalPrice(){
        return rentalPrice;
    }


    public Long getNumberOfBuiltHouses() {
        return numberOfBuiltHouses;
    }


    public Long getNumberOfBuiltHotels() {
        return numberOfBuiltHotels;
    }

    public Boolean hastHotelBuilt(){
        return (numberOfBuiltHotels > 0);
    }
}
