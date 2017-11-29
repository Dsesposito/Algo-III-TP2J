package fiuba.algo3.tp2.model;
import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Cells.*;
import fiuba.algo3.tp2.model.Exceptions.CellNotFoundException;

import java.util.*;

public class Board {

    private List<Cell> cells;

    public Board() {
        cells = new ArrayList<>();
        this.initBoardCells();
    }

    public Cell getCellXPositionsFurtherForward(Cell cell, Long numberOfCellsToMoveForward){
        if(!cells.contains(cell)){
            throw new CellNotFoundException("The cell " + cell.getName() + " is invalid.");
        }

        int index = cells.indexOf(cell) + Math.toIntExact(numberOfCellsToMoveForward);
        int lastPos = cells.size()-1;
        if(index > lastPos){
            index = (index % lastPos) - 1;
        }

        return cells.get(index);
    }

    public Cell getCellXPositionsFurtherBackward(Cell cell, Long numberOfCellsToMoveBackward){
        if(!cells.contains(cell)){
            throw new CellNotFoundException("The cell " + cell.getName() + " is invalid.");
        }

        return cells.get((int) (cells.indexOf(cell) - numberOfCellsToMoveBackward));
    }

    public Cell getStartCell(){
        return cells.get(0);
    }

    public List getCells(){
        return this.cells;
    }

    public Quini6 getQuini6(){
        return (Quini6) cells.stream().filter(cell -> cell.getName().equals("Quini 6")).findFirst().orElse(null);
    }

    public Jail getJail(){
        return (Jail) cells.stream().filter(cell -> cell.getName().equals("Carcel")).findFirst().orElse(null);
    }

    public Police getPolice(){
        return (Police) cells.stream().filter(cell -> cell.getName().equals("Policia")).findFirst().orElse(null);
    }

    public LuxuryTax getLuxuryTax(){
        return (LuxuryTax) cells.stream().filter(cell -> cell.getName().equals("Impuesto Al Lujo")).findFirst().orElse(null);
    }

    public DynamicForward getDynamicForward(){
        return (DynamicForward) cells.stream().filter(cell -> cell.getName().equals("Avance Dinámico")).findFirst().orElse(null);
    }

    public DynamicBackward getDynamicBackward(){
        return (DynamicBackward) cells.stream().filter(cell -> cell.getName().equals("Retroceso Dinámico")).findFirst().orElse(null);
    }

    public Neighborhood getNeighborhoodByName(String name){
        return (Neighborhood) cells.stream().filter(cell -> cell.getName().equals(name)).findFirst().orElse(null);
    }


    private void initBoardCells() {

        Railway train = new Railway("Tren",this, Money.withValue(Global.config.getDouble("trainSalePrice")), Global.config.getDouble("trainDiceMultiplierSingle"), Global.config.getDouble("trainDiceMultiplierGroup"));
        Railway subway = new Railway("Subte",this, Money.withValue(Global.config.getDouble("subwaySalePrice")), Global.config.getDouble("subwayDiceMultiplierSingle"), Global.config.getDouble("subwayDiceMultiplierGroup"));
        CellGroup.group("Transporte ferroviario",Arrays.asList(train,subway));

        Service edesur = new Service("Edesur",this, Money.withValue(Global.config.getDouble("edesurSalePrice")), Global.config.getDouble("edesurDiceMultiplierSingle"), Global.config.getDouble("edesurDiceMultiplierGroup"));
        Service aysa = new Service("Aysa",this, Money.withValue(Global.config.getDouble("aysaSalePrice")), Global.config.getDouble("aysaDiceMultiplierSingle"), Global.config.getDouble("aysaDiceMultiplierGroup"));
        CellGroup.group("Servicio de luz y agua",Arrays.asList(edesur,aysa));

        List<Money> rentalPricesHouses;
        rentalPricesHouses = new ArrayList<>();
        List<Money> rentalPricesHotels;
        rentalPricesHotels = new ArrayList<>();
        rentalPricesHouses.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("bsassurOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("bsassurTwoHousesRentalPrice"))));
        rentalPricesHotels.add(Money.withValue(Global.config.getDouble("bsassurOneHotelRentalPrice")));
        Neighborhood bsassur = new Neighborhood("Bs. As. - Zona Sur", Money.withValue(Global.config.getDouble("bsassurSalePrice")), Money.withValue(Global.config.getDouble("bsassurHousePrice")), Money.withValue(Global.config.getDouble("bsassurHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("bsassurRentalPrice")), rentalPricesHouses, rentalPricesHotels), 2L, this);

        List<Money> rentalPricesHouses1;
        rentalPricesHouses1 = new ArrayList<>();
        List<Money> rentalPricesHotels1;
        rentalPricesHotels1 = new ArrayList<>();
        rentalPricesHouses1.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("bsasnorteOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("bsasnorteTwoHousesRentalPrice"))));
        rentalPricesHotels1.add(Money.withValue(Global.config.getDouble("bsasnorteOneHotelRentalPrice")));
        Neighborhood bsasnorte = new Neighborhood("Bs. As. - Zona Norte", Money.withValue(Global.config.getDouble("bsasnorteSalePrice")), Money.withValue(Global.config.getDouble("bsasnorteHousePrice")), Money.withValue(Global.config.getDouble("bsasnorteHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("bsasnorteRentalPrice")), rentalPricesHouses1, rentalPricesHotels1), 2L, this);

        NeighborhoodZone.group("Bs. As.",Arrays.asList(bsassur,bsasnorte));

        List<Money> rentalPricesHouses2;
        rentalPricesHouses2 = new ArrayList<>();
        List<Money> rentalPricesHotels2;
        rentalPricesHotels2 = new ArrayList<>();
        rentalPricesHouses2.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("cordobasurOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("cordobasurTwoHousesRentalPrice"))));
        rentalPricesHotels2.add(Money.withValue(Global.config.getDouble("cordobasurOneHotelRentalPrice")));
        Neighborhood cordobaSur = new Neighborhood("Cordoba - Sur", Money.withValue(Global.config.getDouble("cordobasurSalePrice")), Money.withValue(Global.config.getDouble("cordobasurHousePrice")), Money.withValue(Global.config.getDouble("cordobasurHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("cordobasurRentalPrice")), rentalPricesHouses2, rentalPricesHotels2), 2L, this);

        List<Money> rentalPricesHouses3;
        rentalPricesHouses3 = new ArrayList<>();
        List<Money> rentalPricesHotels3;
        rentalPricesHotels3 = new ArrayList<>();
        rentalPricesHouses3.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("cordobanorteOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("cordobanorteTwoHousesRentalPrice"))));
        rentalPricesHotels3.add(Money.withValue(Global.config.getDouble("cordobanorteOneHotelRentalPrice")));
        Neighborhood cordobaNorte = new Neighborhood("Cordoba - Norte", Money.withValue(Global.config.getDouble("cordobanorteSalePrice")), Money.withValue(Global.config.getDouble("cordobanorteHousePrice")), Money.withValue(Global.config.getDouble("cordobanorteHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("cordobanorteRentalPrice")), rentalPricesHouses3, rentalPricesHotels3), 2L, this);

        NeighborhoodZone.group("Cordoba",Arrays.asList(cordobaSur,cordobaNorte));

        List<Money> rentalPricesHouses5;
        rentalPricesHouses5 = new ArrayList<>();
        List<Money> rentalPricesHotels5;
        rentalPricesHotels5 = new ArrayList<>();
        rentalPricesHouses5.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("saltanorteOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("saltanorteTwoHousesRentalPrice"))));
        rentalPricesHotels5.add(Money.withValue(Global.config.getDouble("saltanorteOneHotelRentalPrice")));
        Neighborhood saltaNorte = new Neighborhood("Salta - Norte", Money.withValue(Global.config.getDouble("saltanorteSalePrice")), Money.withValue(Global.config.getDouble("saltanorteHousePrice")), Money.withValue(Global.config.getDouble("saltanorteHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("saltanorteRentalPrice")), rentalPricesHouses5, rentalPricesHotels5), 2L, this);

        List<Money> rentalPricesHouses6;
        rentalPricesHouses6 = new ArrayList<>();
        List<Money> rentalPricesHotels6;
        rentalPricesHotels6 = new ArrayList<>();
        rentalPricesHouses6.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("saltanorteOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("saltanorteTwoHousesRentalPrice"))));
        rentalPricesHotels6.add(Money.withValue(Global.config.getDouble("saltanorteOneHotelRentalPrice")));
        Neighborhood saltaSur = new Neighborhood("Salta - Sur", Money.withValue(Global.config.getDouble("saltanorteSalePrice")), Money.withValue(Global.config.getDouble("saltanorteHousePrice")), Money.withValue(Global.config.getDouble("saltanorteHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("saltanorteRentalPrice")), rentalPricesHouses6, rentalPricesHotels6), 2L, this);

        NeighborhoodZone.group("Salta",Arrays.asList(saltaNorte,saltaSur));

        List<Money> rentalPricesHouses4;
        rentalPricesHouses4 = new ArrayList<>();
        List<Money> rentalPricesHotels4;
        rentalPricesHotels4 = new ArrayList<>();
        rentalPricesHouses4.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("santafeOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("santafeTwoHousesRentalPrice"))));
        rentalPricesHotels4.add(Money.withValue(Global.config.getDouble("santafeOneHotelRentalPrice")));
        Neighborhood santaFe = new Neighborhood("Santa Fe", Money.withValue(Global.config.getDouble("santafeSalePrice")), Money.withValue(Global.config.getDouble("santafeHousePrice")), Money.withValue(Global.config.getDouble("santafeHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("santafeRentalPrice")), rentalPricesHouses4, rentalPricesHotels4), 1L,this);

        NeighborhoodZone.group("Santa Fe",Arrays.asList(santaFe));

        List<Money> rentalPricesHouses7;
        rentalPricesHouses7 = new ArrayList<>();
        List<Money> rentalPricesHotels7;
        rentalPricesHotels7 = new ArrayList<>();
        rentalPricesHouses7.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("neuquenOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("neuquenTwoHousesRentalPrice"))));
        rentalPricesHotels7.add(Money.withValue(Global.config.getDouble("neuquenOneHotelRentalPrice")));
        Neighborhood neuquen = new Neighborhood("Neuquén", Money.withValue(Global.config.getDouble("neuquenSalePrice")), Money.withValue(Global.config.getDouble("neuquenHousePrice")), Money.withValue(Global.config.getDouble("neuquenHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("neuquenRentalPrice")), rentalPricesHouses7, rentalPricesHotels7), 1L,this);

        NeighborhoodZone.group("Neuquen",Arrays.asList(neuquen));

        List<Money> rentalPricesHouses8;
        rentalPricesHouses8 = new ArrayList<>();
        List<Money> rentalPricesHotels8;
        rentalPricesHotels8 = new ArrayList<>();
        rentalPricesHouses8.addAll(Arrays.asList(Money.withValue(Global.config.getDouble("tucumanOneHouseRentalPrice")), Money.withValue(Global.config.getDouble("tucumanTwoHousesRentalPrice"))));
        rentalPricesHotels8.add(Money.withValue(Global.config.getDouble("tucumanOneHotelRentalPrice")));
        Neighborhood tucuman = new Neighborhood("Tucuman", Money.withValue(Global.config.getDouble("tucumanSalePrice")), Money.withValue(Global.config.getDouble("tucumanHousePrice")), Money.withValue(Global.config.getDouble("tucumanHotelPrice")), new Rental(Money.withValue(Global.config.getDouble("tucumanRentalPrice")), rentalPricesHouses8, rentalPricesHotels8), 1L,this);

        NeighborhoodZone.group("Tucuman",Arrays.asList(tucuman));

        cells.add(new StartPoint("Salida", this));
        cells.add(new Quini6("Quini 6", this));
        cells.add(bsassur);
        cells.add(edesur);
        cells.add(bsasnorte);
        cells.add(new Jail("Carcel", this));
        cells.add(cordobaSur);
        cells.add(new DynamicForward("Avance Dinámico", this));
        cells.add(subway);
        cells.add(cordobaNorte);
        cells.add(new LuxuryTax("Impuesto Al Lujo", this));
        cells.add(santaFe);
        cells.add(aysa);
        cells.add(saltaNorte);
        cells.add(saltaSur);
        cells.add(new Police("Policia", this));
        cells.add(train);
        cells.add(neuquen);
        cells.add(new DynamicBackward("Retroceso Dinámico", this));
        cells.add(tucuman);
    }

    public Service getServiceByName(String name){
        return (Service) cells.stream()
                .filter(cell -> cell.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Railway getRailwayByName(String name){
        return (Railway) cells.stream()
                .filter(cell -> cell.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Cell getCellByName(String name){
        return cells.stream().filter(cell -> cell.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
