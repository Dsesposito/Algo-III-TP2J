package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Cells.*;
import fiuba.algo3.tp2.model.Exceptions.CellNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    List<Cell> cells;
    List<NeighborhoodZone> zones;

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

    public Service getServiceByName(String name){
        return (Service) cells.stream().filter(cell -> cell.getName().equals(name)).findFirst().orElse(null);
    }

    public Railway getRailwayByName(String name){
        return (Railway) cells.stream().filter(cell -> cell.getName().equals(name)).findFirst().orElse(null);
    }

    private void initBoardCells(){
        Railway train = new Railway("Tren",this, Money.withValue(38000.0),450.0,800.0);
        Railway subway = new Railway("Subte",this, Money.withValue(40000.0),600.0,1100.0);
        CellGroup.groupCells("Transporte ferroviario",Arrays.asList(train,subway));
        cells.add(new StartPoint("Salida",this));
        cells.add(new Quini6("Quini 6",this));
        cells.add(new Neighborhood("Bs. As. - Zona Sur",Money.withValue(20000.0),Money.withValue(5000.0),Money.withValue(8000.0),this));
        cells.add(new Service("Edesur",this,Money.withValue(35000.0),500.0));
        cells.add(new Neighborhood("Bs. As. - Zona Norte",Money.withValue(25000.0),Money.withValue(5500.0),Money.withValue(9000.0),this));
        cells.add(new Jail("Carcel",this));
        cells.add(new Neighborhood("Cordoba - Sur",Money.withValue(18000.0),Money.withValue(2000.0),Money.withValue(3000.0),this));
        cells.add(new DynamicForward("Avance Dinámico",this));
        cells.add(train);
        cells.add(new Neighborhood("Cordoba - Norte",Money.withValue(20000.0),Money.withValue(2200.0),Money.withValue(3500.0),this));
        cells.add(new LuxuryTax("Impuesto De Lujo",this));
        cells.add(new Neighborhood("Santa Fe",Money.withValue(15000.0),Money.withValue(4000.0),Money.withValue(0.0),this));
        cells.add(new Service("Aysa",this, Money.withValue(30000.0),300.0));
        cells.add(new Neighborhood("Salta - Norte",Money.withValue(23000.0),Money.withValue(4500.0),Money.withValue(7500.0),this));
        cells.add(new Neighborhood("Salta - Sur",Money.withValue(23000.0),Money.withValue(4500.0),Money.withValue(7500.0),this));
        cells.add(new Police("Policia",this));
        cells.add(subway);
        cells.add(new Neighborhood("Neuquén",Money.withValue(17000.0),Money.withValue(3800.0),Money.withValue(0.0),this));
        cells.add(new DynamicBackward("Retroceso Dinámico",this));
        cells.add(new Neighborhood("Tucuman",Money.withValue(25000.0),Money.withValue(0.0),Money.withValue(0.0),this));


    }

    private void initZones(){
        zones.add(new NeighborhoodZone("Bs. As.",Arrays.asList(this.getNeighborhoodByName("Bs. As. - Zona Sur"),this.getNeighborhoodByName("Bs. As. - Zona Norte"))));
        zones.add(new NeighborhoodZone("Salta",Arrays.asList(this.getNeighborhoodByName("Salta - Sur"),this.getNeighborhoodByName("Salta - Norte"))));
        zones.add(new NeighborhoodZone("Cordoba",Arrays.asList(this.getNeighborhoodByName("Cordoba - Sur"),this.getNeighborhoodByName("Cordoba - Norte"))));

    }
}
