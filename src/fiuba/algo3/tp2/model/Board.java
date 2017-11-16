package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;
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
        int position = 0;
        for(String cellName : cellsNames) {
            cells.add(new Cell(cellName,this));
            position++;
        }
    }

    public Cell moveFowardXCells(Cell cell,Long numberOfCellsToMoveFoward){

        if(!cells.contains(cell)){
            throw new CellNotFoundException("The cell " + cell.getName() + " is invalid.");
        }

        return cells.get((int) (cells.indexOf(cell) + numberOfCellsToMoveFoward));
    }

    public List getCells(){
        return this.cells;
    }
}
