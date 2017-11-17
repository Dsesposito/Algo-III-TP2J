package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Cells.*;
import fiuba.algo3.tp2.model.Exceptions.CellNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Board {

    List<Cell> cells;

    public Board() {
        cells = new ArrayList<>();
        this.initBoardCells();
    }

    public Cell moveForwardXCells(Cell cell, Long numberOfCellsToMoveForward){
        //TODO: Refactor implementar desborde
        if(!cells.contains(cell)){
            throw new CellNotFoundException("The cell " + cell.getName() + " is invalid.");
        }

        return cells.get((int) (cells.indexOf(cell) + numberOfCellsToMoveForward));
    }

    public Cell moveBackwardXCells(Cell cell, Long numberOfCellsToMoveBackward){
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
        return (Police) cells.stream().filter(cell -> cell.getName().equals("Police")).findFirst().orElse(null);
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

    private void initBoardCells(){
        cells.add(new StartPoint("Salida",this));
        cells.add(new Quini6("Quini 6",this));
        cells.add(new Neighborhood("Bs. As. - Zona Sur",this));
        cells.add(new Service("Edesur",this));
        cells.add(new Neighborhood("Bs. As. - Zona Norte",this));
        cells.add(new Jail("Carcel",this));
        cells.add(new Neighborhood("Cordoba - Sur",this));
        cells.add(new DynamicForward("Avance Dinámico",this));
        cells.add(new Railway("Subte",this));
        cells.add(new Neighborhood("Cordoba - Norte",this));
        cells.add(new LuxuryTax("Impuesto De Lujo",this));
        cells.add(new Neighborhood("Santa Fe",this));
        cells.add(new Service("Aysa",this));
        cells.add(new Neighborhood("Salta - Norte",this));
        cells.add(new Neighborhood("Salta - Sur",this));
        cells.add(new Police("Policia",this));
        cells.add(new Railway("Tren",this));
        cells.add(new Neighborhood("Neuquén",this));
        cells.add(new DynamicBackward("Retroceso Dinámico",this));
        cells.add(new Neighborhood("Tucuman",this));
    }
}
