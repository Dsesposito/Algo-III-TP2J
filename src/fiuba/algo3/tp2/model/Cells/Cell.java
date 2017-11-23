package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

import java.util.Arrays;
import java.util.List;

public abstract class Cell{

    private String name;
    private Board board;
    private CellGroup group;

    public Cell(String name,Board board){
        this.name = name;
        this.board = board;
    }

    protected Boolean cellGroupHasSameOwner(Player player){
        return group.isOwnedBySamePlayer(player);
    }


    public Cell getCellXPositionsFurtherForward(Long numberOfCellsToMoveForward){
        return board.getCellXPositionsFurtherForward(this,numberOfCellsToMoveForward);
    }

    public Cell getCellXPositionsFurtherBackward(Long numberOfCellsToGoesBack){
        return board.getCellXPositionsFurtherBackward(this,numberOfCellsToGoesBack);
    }

    public String getName(){
        return this.name;
    }

    public abstract void playerLandsOnCell(Player player,Turn actualTurn);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return name != null ? name.equals(cell.name) : cell.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public void groupBy(CellGroup group){
        this.group = group;
    }

    public CellGroup getGroup(){
        return this.group;
    }
}
