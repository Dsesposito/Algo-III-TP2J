package fiuba.algo3.tp2.model;
import java.util.List;

public class Rental {
    private Money rentalPrice;
    private  List<Money> rentalPrices;

    public Rental(List<Money> rentalPrices){
        this.rentalPrices = rentalPrices;
        this.rentalPrice = this.rentalPrices.get(0);
    }

    public void updateRentalPrice(int index){
        this.rentalPrice = this.rentalPrices.get(index);
    }

}
