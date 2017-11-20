package fiuba.algo3.tp2.model;
import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Cells.*;
import fiuba.algo3.tp2.model.Exceptions.CellNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private List<Cell> cells;
    private List<NeighborhoodZone> zones;

    public Board() {
        cells = new ArrayList<>();
        zones = new ArrayList<>();
        this.initBoardCells();
        this.initZones();
    }

    public Cell getCellXPositionsFurtherForward(Cell cell, Long numberOfCellsToMoveForward){
        //TODO: Refactor implementar desborde
        if(!cells.contains(cell)){
            throw new CellNotFoundException("The cell " + cell.getName() + " is invalid.");
        }

        return cells.get((int) (cells.indexOf(cell) + numberOfCellsToMoveForward));
    }

    public Cell getCellXPositionsFurtherBackward(Cell cell, Long numberOfCellsToMoveBackward){
        //TODO: Refactor implementar desborde
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
        cells.add(new StartPoint("Salida", this));

        cells.add(new Quini6("Quini 6", this));

        List<Money> rentalPricesHouses;
        rentalPricesHouses = new ArrayList<>();
        List<Money> rentalPricesHotels;
        rentalPricesHotels = new ArrayList<>();
        rentalPricesHouses.addAll(Arrays.asList(Money.withValue(3000.0), Money.withValue(3500.0)));
        rentalPricesHotels.add(Money.withValue(5000.0));
        cells.add(new Neighborhood("Bs. As. - Zona Sur", Money.withValue(20000.0), Money.withValue(5000.0), Money.withValue(8000.0), new Rental(Money.withValue(2000.0), rentalPricesHouses, rentalPricesHotels), this));

        cells.add(new Service("Edesur", this));

        List<Money> rentalPricesHouses1;
        rentalPricesHouses1 = new ArrayList<>();
        List<Money> rentalPricesHotels1;
        rentalPricesHotels1 = new ArrayList<>();
        rentalPricesHouses1.addAll(Arrays.asList(Money.withValue(3500.0), Money.withValue(4000.0)));
        rentalPricesHotels1.add(Money.withValue(6000.0));
        cells.add(new Neighborhood("Bs. As. - Zona Norte", Money.withValue(25000.0), Money.withValue(5500.0), Money.withValue(9000.0), new Rental(Money.withValue(2500.0), rentalPricesHouses1, rentalPricesHotels1), this));

        cells.add(new Jail("Carcel", this));

        List<Money> rentalPricesHouses2;
        rentalPricesHouses2 = new ArrayList<>();
        List<Money> rentalPricesHotels2;
        rentalPricesHotels2 = new ArrayList<>();
        rentalPricesHouses2.addAll(Arrays.asList(Money.withValue(1500.0), Money.withValue(2500.0)));
        rentalPricesHotels2.add(Money.withValue(3000.0));
        cells.add(new Neighborhood("Cordoba - Sur",Money.withValue(18000.0),Money.withValue(2000.0), Money.withValue(3000.0), new Rental(Money.withValue(1000.0), rentalPricesHouses2, rentalPricesHotels2), this));

        cells.add(new DynamicForward("Avance Dinámico",this));

        cells.add(new Railway("Subte",this));

        List<Money> rentalPricesHouses3;
        rentalPricesHouses3 = new ArrayList<>();
        List<Money> rentalPricesHotels3;
        rentalPricesHotels3 = new ArrayList<>();
        rentalPricesHouses3.addAll(Arrays.asList(Money.withValue(1800.0), Money.withValue(2900.0)));
        rentalPricesHotels3.add(Money.withValue(3500.0));
        cells.add(new Neighborhood("Cordoba - Norte",Money.withValue(20000.0),Money.withValue(2200.0), Money.withValue(3500.0), new Rental(Money.withValue(1300.0), rentalPricesHouses3, rentalPricesHotels3), this));

        cells.add(new LuxuryTax("Impuesto De Lujo",this));

        List<Money> rentalPricesHouses4;
        rentalPricesHouses4 = new ArrayList<>();
        List<Money> rentalPricesHotels4;
        rentalPricesHotels4 = new ArrayList<>();
        rentalPricesHouses4.add(Money.withValue(3500.0));
        rentalPricesHotels4.add(Money.withValue(0.0));
        cells.add(new Neighborhood("Santa Fe",Money.withValue(15000.0),Money.withValue(4000.0), Money.withValue(0.0), new Rental(Money.withValue(1500.0), rentalPricesHouses4, rentalPricesHotels4), this));

        cells.add(new Service("Aysa",this));

        List<Money> rentalPricesHouses5;
        rentalPricesHouses5 = new ArrayList<>();
        List<Money> rentalPricesHotels5;
        rentalPricesHotels5 = new ArrayList<>();
        rentalPricesHouses.addAll(Arrays.asList(Money.withValue(3250.0), Money.withValue(3850.0)));
        rentalPricesHotels.add(Money.withValue(5500.0));
        cells.add(new Neighborhood("Salta - Norte",Money.withValue(23000.0),Money.withValue(4500.0), Money.withValue(7500.0), new Rental(Money.withValue(2000.0), rentalPricesHouses5, rentalPricesHotels5), this));

        List<Money> rentalPricesHouses6;
        rentalPricesHouses6 = new ArrayList<>();
        List<Money> rentalPricesHotels6;
        rentalPricesHotels6 = new ArrayList<>();
        rentalPricesHouses6.addAll(Arrays.asList(Money.withValue(3250.0), Money.withValue(3850.0)));
        rentalPricesHotels6.add(Money.withValue(5500.0));
        cells.add(new Neighborhood("Salta - Sur",Money.withValue(23000.0),Money.withValue(4500.0), Money.withValue(7500.0), new Rental(Money.withValue(2000.0), rentalPricesHouses6, rentalPricesHotels6), this));

        cells.add(new Police("Policia",this));

        cells.add(new Railway("Tren",this));

        List<Money> rentalPricesHouses7;
        rentalPricesHouses7 = new ArrayList<>();
        List<Money> rentalPricesHotels7;
        rentalPricesHotels7 = new ArrayList<>();
        rentalPricesHouses7.add(Money.withValue(3800.0));
        rentalPricesHotels7.add(Money.withValue(0.0));
        cells.add(new Neighborhood("Neuquén",Money.withValue(17000.0),Money.withValue(3800.0), Money.withValue(0.0), new Rental(Money.withValue(1800.0), rentalPricesHouses7, rentalPricesHotels7), this));

        cells.add(new DynamicBackward("Retroceso Dinámico",this));

        List<Money> rentalPricesHouses8;
        rentalPricesHouses8 = new ArrayList<>();
        List<Money> rentalPricesHotels8;
        rentalPricesHotels8 = new ArrayList<>();
        rentalPricesHouses8.add(Money.withValue(4500.0));
        rentalPricesHotels8.add(Money.withValue(0.0));
        cells.add(new Neighborhood("Tucuman",Money.withValue(25000.0),Money.withValue(0.0), Money.withValue(0.0), new Rental(Money.withValue(2500.0), rentalPricesHouses8, rentalPricesHotels8), this));
    }

    private void initZones(){
        zones.add(new NeighborhoodZone("Bs. As.",Arrays.asList(this.getNeighborhoodByName("Bs. As. - Zona Sur"),this.getNeighborhoodByName("Bs. As. - Zona Norte"))));
        zones.add(new NeighborhoodZone("Salta",Arrays.asList(this.getNeighborhoodByName("Salta - Sur"),this.getNeighborhoodByName("Salta - Norte"))));
        zones.add(new NeighborhoodZone("Cordoba",Arrays.asList(this.getNeighborhoodByName("Cordoba - Sur"),this.getNeighborhoodByName("Cordoba - Norte"))));

    }
}
