package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;

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
            cells.add(new Cell(cellName));
            position++;
        }
    }

    public List getCells(){
        return this.cells;
    }
}
