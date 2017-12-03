package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Money;
import fiuba.algo3.tp2.model.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CellsFactory {

    public static Railway getInstanceOfTrain(Board board){
        return new Railway("Tren", board, Money.withValue(Global.config.getDouble("trainSalePrice")), Global.config.getDouble("trainDiceMultiplierSingle"), Global.config.getDouble("trainDiceMultiplierGroup"), Position.inXY(1030, 160));
    }

    public static Railway getInstanceOfSubway(Board board) {
        return new Railway("Subte", board, Money.withValue(Global.config.getDouble("subwaySalePrice")), Global.config.getDouble("subwayDiceMultiplierSingle"), Global.config.getDouble("subwayDiceMultiplierGroup"), Position.inXY(80, 260));
    }

    public static Service getInstanceOfEdesur(Board board) {
        return new Service("Edesur", board, Money.withValue(Global.config.getDouble("edesurSalePrice")), Global.config.getDouble("edesurDiceMultiplierSingle"), Global.config.getDouble("edesurDiceMultiplierGroup"),Position.inXY(470, 570));
    }

    public static Service getInstanceOfAysa(Board board) {
        return new Service("Aysa", board, Money.withValue(Global.config.getDouble("aysaSalePrice")), Global.config.getDouble("aysaDiceMultiplierSingle"), Global.config.getDouble("aysaDiceMultiplierGroup"),Position.inXY(470, 50));
    }

    public static Neighborhood getInstanceOfBsAsSur(Board board) {
        List<Money> rentalPricesHouses;
        rentalPricesHouses = new ArrayList<>();
        List<Money> rentalPricesHotels;
        rentalPricesHotels = new ArrayList<>();
        rentalPricesHouses.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("bsassurOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("bsassurTwoHousesRentalPrice"))));
        rentalPricesHotels.add(Money.withValue(Global.config.getDouble("bsassurOneHotelRentalPrice")));
        return new Neighborhood("Bs. As. - Zona Sur", Money.withValue(Global.config.getDouble("bsassurSalePrice")), Money.withValue(Global.config.getDouble("bsassurHousePrice")), Money.withValue(Global.config.getDouble("bsassurHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("bsassurRentalPrice")), rentalPricesHouses, rentalPricesHotels), 2L, board, Position.inXY(640,570));
    }

    public static Neighborhood getInstanceOfBsAsNorte(Board board) {
        List<Money> rentalPricesHouses1;
        rentalPricesHouses1 = new ArrayList<>();
        List<Money> rentalPricesHotels1;
        rentalPricesHotels1 = new ArrayList<>();
        rentalPricesHouses1.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("bsasnorteOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("bsasnorteTwoHousesRentalPrice"))));
        rentalPricesHotels1.add(Money.withValue(Global.config.getDouble("bsasnorteOneHotelRentalPrice")));
        return new Neighborhood("Bs. As. - Zona Norte", Money.withValue(Global.config.getDouble("bsasnorteSalePrice")), Money.withValue(Global.config.getDouble("bsasnorteHousePrice")), Money.withValue(Global.config.getDouble("bsasnorteHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("bsasnorteRentalPrice")), rentalPricesHouses1, rentalPricesHotels1), 2L, board, Position.inXY(270, 570));
    }

    public static Neighborhood getInstanceOfCordobaSur(Board board) {
        List<Money> rentalPricesHouses2;
        rentalPricesHouses2 = new ArrayList<>();
        List<Money> rentalPricesHotels2;
        rentalPricesHotels2 = new ArrayList<>();
        rentalPricesHouses2.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("cordobasurOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("cordobasurTwoHousesRentalPrice"))));
        rentalPricesHotels2.add(Money.withValue(Global.config.getDouble("cordobasurOneHotelRentalPrice")));
        return new Neighborhood("Cordoba - Sur", Money.withValue(Global.config.getDouble("cordobasurSalePrice")), Money.withValue(Global.config.getDouble("cordobasurHousePrice")), Money.withValue(Global.config.getDouble("cordobasurHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("cordobasurRentalPrice")), rentalPricesHouses2, rentalPricesHotels2), 2L, board, Position.inXY(90, 460));
    }

    public static Neighborhood getInstanceOfCordobaNorte(Board board) {
        List<Money> rentalPricesHouses3;
        rentalPricesHouses3 = new ArrayList<>();
        List<Money> rentalPricesHotels3;
        rentalPricesHotels3 = new ArrayList<>();
        rentalPricesHouses3.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("cordobanorteOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("cordobanorteTwoHousesRentalPrice"))));
        rentalPricesHotels3.add(Money.withValue(Global.config.getDouble("cordobanorteOneHotelRentalPrice")));
        return new Neighborhood("Cordoba - Norte", Money.withValue(Global.config.getDouble("cordobanorteSalePrice")), Money.withValue(Global.config.getDouble("cordobanorteHousePrice")), Money.withValue(Global.config.getDouble("cordobanorteHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("cordobanorteRentalPrice")), rentalPricesHouses3, rentalPricesHotels3), 2L, board, Position.inXY(90, 150));
    }

    public static Neighborhood getInstanceOfSaltaNorte(Board board) {
        List<Money> rentalPricesHouses5;
        rentalPricesHouses5 = new ArrayList<>();
        List<Money> rentalPricesHotels5;
        rentalPricesHotels5 = new ArrayList<>();
        rentalPricesHouses5.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("saltanorteOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("saltanorteTwoHousesRentalPrice"))));
        rentalPricesHotels5.add(Money.withValue(Global.config.getDouble("saltanorteOneHotelRentalPrice")));
        return new Neighborhood("Salta - Norte", Money.withValue(Global.config.getDouble("saltanorteSalePrice")), Money.withValue(Global.config.getDouble("saltanorteHousePrice")), Money.withValue(Global.config.getDouble("saltanorteHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("saltanorteRentalPrice")), rentalPricesHouses5, rentalPricesHotels5), 2L, board, Position.inXY(650, 50));
    }

    public static Neighborhood getInstanceOfSaltaSur(Board board) {
        List<Money> rentalPricesHouses6;
        rentalPricesHouses6 = new ArrayList<>();
        List<Money> rentalPricesHotels6;
        rentalPricesHotels6 = new ArrayList<>();
        rentalPricesHouses6.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("saltasurOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("saltasurTwoHousesRentalPrice"))));
        rentalPricesHotels6.add(Money.withValue(Global.config.getDouble("saltasurOneHotelRentalPrice")));
        return new Neighborhood("Salta - Sur", Money.withValue(Global.config.getDouble("saltasurSalePrice")), Money.withValue(Global.config.getDouble("saltasurHousePrice")), Money.withValue(Global.config.getDouble("saltasurHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("saltasurRentalPrice")), rentalPricesHouses6, rentalPricesHotels6), 2L, board, Position.inXY(850, 50));
    }

    public static Neighborhood getInstanceOfSantaFe(Board board) {
        List<Money> rentalPricesHouses4;
        rentalPricesHouses4 = new ArrayList<>();
        List<Money> rentalPricesHotels4;
        rentalPricesHotels4 = new ArrayList<>();
        rentalPricesHouses4.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("santafeOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("santafeTwoHousesRentalPrice"))));
        rentalPricesHotels4.add(Money.withValue(Global.config.getDouble("santafeOneHotelRentalPrice")));
        return new Neighborhood("Santa Fe", Money.withValue(Global.config.getDouble("santafeSalePrice")), Money.withValue(Global.config.getDouble("santafeHousePrice")), Money.withValue(Global.config.getDouble("santafeHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("santafeRentalPrice")), rentalPricesHouses4, rentalPricesHotels4), 1L, board,Position.inXY(270, 50));
    }

    public static Neighborhood getInstanceOfNeuquen(Board board) {
        List<Money> rentalPricesHouses7;
        rentalPricesHouses7 = new ArrayList<>();
        List<Money> rentalPricesHotels7;
        rentalPricesHotels7 = new ArrayList<>();
        rentalPricesHouses7.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("neuquenOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("neuquenTwoHousesRentalPrice"))));
        rentalPricesHotels7.add(Money.withValue(Global.config.getDouble("neuquenOneHotelRentalPrice")));
        return new Neighborhood("Neuquén", Money.withValue(Global.config.getDouble("neuquenSalePrice")), Money.withValue(Global.config.getDouble("neuquenHousePrice")), Money.withValue(Global.config.getDouble("neuquenHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("neuquenRentalPrice")), rentalPricesHouses7, rentalPricesHotels7), 1L, board,Position.inXY(1030, 260));
    }

    public static Neighborhood getInstanceOfTucuman(Board board) {
        List<Money> rentalPricesHouses8;
        rentalPricesHouses8 = new ArrayList<>();
        List<Money> rentalPricesHotels8;
        rentalPricesHotels8 = new ArrayList<>();
        rentalPricesHouses8.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("tucumanOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("tucumanTwoHousesRentalPrice"))));
        rentalPricesHotels8.add(Money.withValue(Global.config.getDouble("tucumanOneHotelRentalPrice")));
        return new Neighborhood("Tucuman", Money.withValue(Global.config.getDouble("tucumanSalePrice")), Money.withValue(Global.config.getDouble("tucumanHousePrice")), Money.withValue(Global.config.getDouble("tucumanHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("tucumanRentalPrice")), rentalPricesHouses8, rentalPricesHotels8), 1L, board,Position.inXY(1030, 460));

    }

    public static StartPoint getInstanceOfStartPoint(Board board) {
        return new StartPoint("Salida", board, Position.inXY(1030,570));
    }

    public static Quini6 getInstanceOfQuini6(Board board) {
        return new Quini6("Quini 6", board, Position.inXY(830,570));
    }

    public static Jail getInstanceOfJail(Board board) {
        return new Jail("Carcel", board,Position.inXY(90, 570));
    }

    public static DynamicForward getInstanceOfDynamicForward(Board board) {
        return new DynamicForward("Avance Dinámico", board, Position.inXY(90, 360));
    }

    public static LuxuryTax getInstanceOfLuxuryTax(Board board) {
        return new LuxuryTax("Impuesto Al Lujo", board, Position.inXY(90, 50));
    }

    public static Police getInstanceOfPolicia(Board board) {
        return new Police("Policia", board, Position.inXY(1030, 50));
    }

    public static DynamicBackward getInstanceOfDynamicBackward(Board board) {
        return new DynamicBackward("Retroceso Dinámico", board,Position.inXY(1030, 360));
    }
}
