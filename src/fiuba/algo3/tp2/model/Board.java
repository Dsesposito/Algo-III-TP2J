package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Exceptions.CellNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Board {

    List<Cell> cells;
    List<Player> players;

    public Board() {
        cells = new ArrayList<>();
        players = new ArrayList<>();
        String[] cellsNames = Global.config.getString("board.cells").split(",");
        for(String cellName : cellsNames) {
            cells.add(new Cell(cellName,this));
        }
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

    public List getCells(){
        return this.cells;
    }
}
