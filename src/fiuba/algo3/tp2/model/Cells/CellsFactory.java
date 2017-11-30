package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CellsFactory {

    public static Railway getInstanceOfTrain(Board board){
        return new Railway("Tren",board, Money.withValue(38000.0),450.0,800.0);
    }

    public static Railway getInstanceOfSubway(Board board) {
        return new Railway("Subte",board, Money.withValue(40000.0),600.0,1100.0);
    }

    public static Service getInstanceOfEdesur(Board board) {
        return new Service("Edesur",board,Money.withValue(35000.0),500.0, 1000.0);
    }

    public static Service getInstanceOfAysa(Board board) {
        return new Service("Aysa",board, Money.withValue(30000.0),300.0, 500.0);
    }

    public static Neighborhood getInstanceOfBsAsSur(Board board) {
        List<Money> rentalPricesHouses;
        rentalPricesHouses = new ArrayList<>();
        List<Money> rentalPricesHotels;
        rentalPricesHotels = new ArrayList<>();
        rentalPricesHouses.addAll(Arrays.asList(Money.withValue(3000.0), Money.withValue(3500.0)));
        rentalPricesHotels.add(Money.withValue(5000.0));
        return new Neighborhood("Bs. As. - Zona Sur", Money.withValue(20000.0), Money.withValue(5000.0), Money.withValue(8000.0), new Rental(Money.withValue(2000.0), rentalPricesHouses, rentalPricesHotels), 2L, board);
    }

    public static Neighborhood getInstanceOfBsAsNorte(Board board) {
        List<Money> rentalPricesHouses1;
        rentalPricesHouses1 = new ArrayList<>();
        List<Money> rentalPricesHotels1;
        rentalPricesHotels1 = new ArrayList<>();
        rentalPricesHouses1.addAll(Arrays.asList(Money.withValue(3500.0), Money.withValue(4000.0)));
        rentalPricesHotels1.add(Money.withValue(6000.0));
        return new Neighborhood("Bs. As. - Zona Norte", Money.withValue(25000.0), Money.withValue(5500.0), Money.withValue(9000.0), new Rental(Money.withValue(2500.0), rentalPricesHouses1, rentalPricesHotels1), 2L, board);
    }

    public static Neighborhood getInstanceOfCordobaSur(Board board) {
        List<Money> rentalPricesHouses2;
        rentalPricesHouses2 = new ArrayList<>();
        List<Money> rentalPricesHotels2;
        rentalPricesHotels2 = new ArrayList<>();
        rentalPricesHouses2.addAll(Arrays.asList(Money.withValue(1500.0), Money.withValue(2500.0)));
        rentalPricesHotels2.add(Money.withValue(3000.0));
        return new Neighborhood("Cordoba - Sur", Money.withValue(18000.0), Money.withValue(2000.0), Money.withValue(3000.0), new Rental(Money.withValue(1000.0), rentalPricesHouses2, rentalPricesHotels2), 2L, board);
    }

    public static Neighborhood getInstanceOfCordobaNorte(Board board) {
        List<Money> rentalPricesHouses3;
        rentalPricesHouses3 = new ArrayList<>();
        List<Money> rentalPricesHotels3;
        rentalPricesHotels3 = new ArrayList<>();
        rentalPricesHouses3.addAll(Arrays.asList(Money.withValue(1800.0), Money.withValue(2900.0)));
        rentalPricesHotels3.add(Money.withValue(3500.0));
        return new Neighborhood("Cordoba - Norte", Money.withValue(20000.0), Money.withValue(2200.0), Money.withValue(3500.0), new Rental(Money.withValue(1300.0), rentalPricesHouses3, rentalPricesHotels3), 2L, board);
    }

    public static Neighborhood getInstanceOfSaltaNorte(Board board) {
        List<Money> rentalPricesHouses5;
        rentalPricesHouses5 = new ArrayList<>();
        List<Money> rentalPricesHotels5;
        rentalPricesHotels5 = new ArrayList<>();
        rentalPricesHouses5.addAll(Arrays.asList(Money.withValue(3250.0), Money.withValue(3850.0)));
        rentalPricesHotels5.add(Money.withValue(5500.0));
        return new Neighborhood("Salta - Norte", Money.withValue(23000.0), Money.withValue(4500.0), Money.withValue(7500.0), new Rental(Money.withValue(2000.0), rentalPricesHouses5, rentalPricesHotels5), 2L, board);
    }

    public static Neighborhood getInstanceOfSaltaSur(Board board) {
        List<Money> rentalPricesHouses6;
        rentalPricesHouses6 = new ArrayList<>();
        List<Money> rentalPricesHotels6;
        rentalPricesHotels6 = new ArrayList<>();
        rentalPricesHouses6.addAll(Arrays.asList(Money.withValue(3250.0), Money.withValue(3850.0)));
        rentalPricesHotels6.add(Money.withValue(5500.0));
        return new Neighborhood("Salta - Sur", Money.withValue(23000.0), Money.withValue(4500.0), Money.withValue(7500.0), new Rental(Money.withValue(2000.0), rentalPricesHouses6, rentalPricesHotels6), 2L, board);
    }

    public static Neighborhood getInstanceOfSantaFe(Board board) {
        List<Money> rentalPricesHouses4;
        rentalPricesHouses4 = new ArrayList<>();
        List<Money> rentalPricesHotels4;
        rentalPricesHotels4 = new ArrayList<>();
        rentalPricesHouses4.add(Money.withValue(3500.0));
        rentalPricesHotels4.add(Money.withValue(0.0));
        return new Neighborhood("Santa Fe", Money.withValue(15000.0), Money.withValue(4000.0), Money.withValue(0.0), new Rental(Money.withValue(1500.0), rentalPricesHouses4, rentalPricesHotels4), 1L,board);
    }

    public static Neighborhood getInstanceOfNeuquen(Board board) {
        List<Money> rentalPricesHouses7;
        rentalPricesHouses7 = new ArrayList<>();
        List<Money> rentalPricesHotels7;
        rentalPricesHotels7 = new ArrayList<>();
        rentalPricesHouses7.add(Money.withValue(3800.0));
        rentalPricesHotels7.add(Money.withValue(0.0));
        return new Neighborhood("Neuquén", Money.withValue(17000.0), Money.withValue(4800.0), Money.withValue(0.0), new Rental(Money.withValue(1800.0), rentalPricesHouses7, rentalPricesHotels7), 1L,board);
    }

    public static Neighborhood getInstanceOfTucuman(Board board) {
        List<Money> rentalPricesHouses8;
        rentalPricesHouses8 = new ArrayList<>();
        List<Money> rentalPricesHotels8;
        rentalPricesHotels8 = new ArrayList<>();
        rentalPricesHouses8.add(Money.withValue(4500.0));
        rentalPricesHotels8.add(Money.withValue(0.0));
        return new Neighborhood("Tucuman", Money.withValue(25000.0), Money.withValue(7000.0), Money.withValue(0.0), new Rental(Money.withValue(2500.0), rentalPricesHouses8, rentalPricesHotels8), 1L,board);
    }

    public static StartPoint getInstanceOfStartPoint(Board board) {
        return new StartPoint("Salida", board);
    }

    public static Quini6 getInstanceOfQuini6(Board board) {
        return new Quini6("Quini 6", board);
    }

    public static Jail getInstanceOfJail(Board board) {
        return new Jail("Carcel", board);
    }

    public static DynamicForward getInstanceOfDynamicForward(Board board) {
        return new DynamicForward("Avance Dinámico", board);
    }

    public static LuxuryTax getInstanceOfLuxuryTax(Board board) {
        return new LuxuryTax("Impuesto Al Lujo", board);
    }

    public static Police getInstanceOfPolicia(Board board) {
        return new Police("Policia", board);
    }

    public static DynamicBackward getInstanceOfDynamicBackward(Board board) {
        return new DynamicBackward("Retroceso Dinámico", board);
    }
}
