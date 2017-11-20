package fiuba.algo3.tp2.model;
import java.util.List;
import static java.lang.Math.toIntExact;

public class Rental {
    private Money rentalPrice;
    private  List<Money> rentalPricesDueHouses;
    private  List<Money> rentalPricesDueHotels;

    public Rental(Money rentalPrice, List<Money> rentalPricesDueHouses, List<Money> rentalPricesDueHotels){
        this.rentalPricesDueHouses = rentalPricesDueHouses;
        this.rentalPricesDueHotels = rentalPricesDueHotels;
        this.rentalPrice = rentalPrice;
    }

    public void updateRentalPriceDueHouses(Long index){
        int index_int;
        index_int = toIntExact(index);
        this.rentalPrice = this.rentalPricesDueHouses.get(index_int);
    }

    public void updateRentalPriceDueHotels(Long index){
        int index_int;
        index_int = toIntExact(index);
        this.rentalPrice = this.rentalPricesDueHotels.get(index_int);
    }

    public Money getRentalPrice(){
        return this.rentalPrice;
    }
}