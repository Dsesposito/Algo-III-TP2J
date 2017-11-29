package fiuba.algo3.tp2.model;
import fiuba.algo3.tp2.model.Cells.*;
import fiuba.algo3.tp2.model.Exceptions.CellNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        Railway train = new Railway("Tren",this, Money.withValue(38000.0),450.0,800.0);
        Railway subway = new Railway("Subte",this, Money.withValue(40000.0),600.0,1100.0);
        CellGroup.group("Transporte ferroviario",Arrays.asList(train,subway));

        Service edesur = new Service("Edesur",this,Money.withValue(35000.0),500.0, 1000.0);
        Service aysa = new Service("Aysa",this, Money.withValue(30000.0),300.0, 500.0);
        CellGroup.group("Servicio de luz y agua",Arrays.asList(edesur,aysa));


        List<Money> rentalPricesHouses;
        rentalPricesHouses = new ArrayList<>();
        List<Money> rentalPricesHotels;
        rentalPricesHotels = new ArrayList<>();
        rentalPricesHouses.addAll(Arrays.asList(Money.withValue(3000.0), Money.withValue(3500.0)));
        rentalPricesHotels.add(Money.withValue(5000.0));
        Neighborhood bsassur = new Neighborhood("Bs. As. - Zona Sur", Money.withValue(20000.0), Money.withValue(5000.0), Money.withValue(8000.0), new Rental(Money.withValue(2000.0), rentalPricesHouses, rentalPricesHotels), 2L, this);


        List<Money> rentalPricesHouses1;
        rentalPricesHouses1 = new ArrayList<>();
        List<Money> rentalPricesHotels1;
        rentalPricesHotels1 = new ArrayList<>();
        rentalPricesHouses1.addAll(Arrays.asList(Money.withValue(3500.0), Money.withValue(4000.0)));
        rentalPricesHotels1.add(Money.withValue(6000.0));
        Neighborhood bsasnorte = new Neighborhood("Bs. As. - Zona Norte", Money.withValue(25000.0), Money.withValue(5500.0), Money.withValue(9000.0), new Rental(Money.withValue(2500.0), rentalPricesHouses1, rentalPricesHotels1), 2L, this);

        NeighborhoodZone.group("Bs. As.",Arrays.asList(bsassur,bsasnorte));

        List<Money> rentalPricesHouses2;
        rentalPricesHouses2 = new ArrayList<>();
        List<Money> rentalPricesHotels2;
        rentalPricesHotels2 = new ArrayList<>();
        rentalPricesHouses2.addAll(Arrays.asList(Money.withValue(1500.0), Money.withValue(2500.0)));
        rentalPricesHotels2.add(Money.withValue(3000.0));
        Neighborhood cordobaSur = new Neighborhood("Cordoba - Sur", Money.withValue(18000.0), Money.withValue(2000.0), Money.withValue(3000.0), new Rental(Money.withValue(1000.0), rentalPricesHouses2, rentalPricesHotels2), 2L, this);

        List<Money> rentalPricesHouses3;
        rentalPricesHouses3 = new ArrayList<>();
        List<Money> rentalPricesHotels3;
        rentalPricesHotels3 = new ArrayList<>();
        rentalPricesHouses3.addAll(Arrays.asList(Money.withValue(1800.0), Money.withValue(2900.0)));
        rentalPricesHotels3.add(Money.withValue(3500.0));
        Neighborhood cordobaNorte = new Neighborhood("Cordoba - Norte", Money.withValue(20000.0), Money.withValue(2200.0), Money.withValue(3500.0), new Rental(Money.withValue(1300.0), rentalPricesHouses3, rentalPricesHotels3), 2L, this);

        NeighborhoodZone.group("Cordoba",Arrays.asList(cordobaSur,cordobaNorte));

        List<Money> rentalPricesHouses5;
        rentalPricesHouses5 = new ArrayList<>();
        List<Money> rentalPricesHotels5;
        rentalPricesHotels5 = new ArrayList<>();
        rentalPricesHouses5.addAll(Arrays.asList(Money.withValue(3250.0), Money.withValue(3850.0)));
        rentalPricesHotels5.add(Money.withValue(5500.0));
        Neighborhood saltaNorte = new Neighborhood("Salta - Norte", Money.withValue(23000.0), Money.withValue(4500.0), Money.withValue(7500.0), new Rental(Money.withValue(2000.0), rentalPricesHouses5, rentalPricesHotels5), 2L, this);

        List<Money> rentalPricesHouses6;
        rentalPricesHouses6 = new ArrayList<>();
        List<Money> rentalPricesHotels6;
        rentalPricesHotels6 = new ArrayList<>();
        rentalPricesHouses6.addAll(Arrays.asList(Money.withValue(3250.0), Money.withValue(3850.0)));
        rentalPricesHotels6.add(Money.withValue(5500.0));
        Neighborhood saltaSur = new Neighborhood("Salta - Sur", Money.withValue(23000.0), Money.withValue(4500.0), Money.withValue(7500.0), new Rental(Money.withValue(2000.0), rentalPricesHouses6, rentalPricesHotels6), 2L, this);

        NeighborhoodZone.group("Salta",Arrays.asList(saltaNorte,saltaSur));



        List<Money> rentalPricesHouses4;
        rentalPricesHouses4 = new ArrayList<>();
        List<Money> rentalPricesHotels4;
        rentalPricesHotels4 = new ArrayList<>();
        rentalPricesHouses4.add(Money.withValue(3500.0));
        rentalPricesHotels4.add(Money.withValue(0.0));
        Neighborhood santaFe = new Neighborhood("Santa Fe", Money.withValue(15000.0), Money.withValue(4000.0), Money.withValue(0.0), new Rental(Money.withValue(1500.0), rentalPricesHouses4, rentalPricesHotels4), 1L,this);

        NeighborhoodZone.group("Santa Fe",Arrays.asList(santaFe));



        List<Money> rentalPricesHouses7;
        rentalPricesHouses7 = new ArrayList<>();
        List<Money> rentalPricesHotels7;
        rentalPricesHotels7 = new ArrayList<>();
        rentalPricesHouses7.add(Money.withValue(3800.0));
        rentalPricesHotels7.add(Money.withValue(0.0));
        Neighborhood neuquen = new Neighborhood("Neuquén", Money.withValue(17000.0), Money.withValue(4800.0), Money.withValue(0.0), new Rental(Money.withValue(1800.0), rentalPricesHouses7, rentalPricesHotels7), 1L,this);

        NeighborhoodZone.group("Neuquen",Arrays.asList(neuquen));



        List<Money> rentalPricesHouses8;
        rentalPricesHouses8 = new ArrayList<>();
        List<Money> rentalPricesHotels8;
        rentalPricesHotels8 = new ArrayList<>();
        rentalPricesHouses8.add(Money.withValue(4500.0));
        rentalPricesHotels8.add(Money.withValue(0.0));
        Neighborhood tucuman = new Neighborhood("Tucuman", Money.withValue(25000.0), Money.withValue(7000.0), Money.withValue(0.0), new Rental(Money.withValue(2500.0), rentalPricesHouses8, rentalPricesHotels8), 1L,this);

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
