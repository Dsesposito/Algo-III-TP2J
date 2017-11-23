package fiuba.algo3.tp2.model;
import java.util.List;

public class Rental {
    private Money rentalPriceWithoutBuildings;
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
    }

    public void incrementBuiltHouses(){
        this.numberOfBuiltHouses++;
    }

    public void decrementBuiltHouses(){
        this.numberOfBuiltHouses--;
    }

    public void incrementBuiltHotels(){
        this.numberOfBuiltHotels++;
    }

    public void decrementBuiltHotels(){
        this.numberOfBuiltHotels--;
    }

    public void clearBuiltHouses(){
        this.numberOfBuiltHouses = 0L;
    }

    public void clearBuiltHousesAndHotels(){
        this.numberOfBuiltHouses = 0L;
        this.numberOfBuiltHotels = 0L;
    }

    public Money getRentalPrice(){
        if(this.getNumberOfBuiltHotels() == 0 && this.getNumberOfBuiltHouses() == 0){
            return this.rentalPriceWithoutBuildings;
        }
        else{
            if(this.getNumberOfBuiltHotels() == 0){
                return this.rentalPricesDueHouses.get(Math.toIntExact(this.getNumberOfBuiltHouses()) - 1);
            }
            else{
                return this.rentalPricesDueHotels.get(Math.toIntExact(this.getNumberOfBuiltHotels()) - 1);
            }
        }
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
