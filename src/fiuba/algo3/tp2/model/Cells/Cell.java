package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Position;
import fiuba.algo3.tp2.model.Turn;

import java.util.ArrayList;
import java.util.List;

public abstract class Cell{

    private String name;
    private Board board;
    private CellGroup group;
    private Position position;
    private List<Player> playersOnCell;

    public Cell(String name, Board board, Position position){
        this.name = name;
        this.board = board;
        this.position = position;
        this.playersOnCell = new ArrayList<>();
    }

    protected Boolean cellGroupHasSameOwner(Player player){
        return group.isOwnedBySamePlayer(player);
    }


    public Cell getCellXPositionsFurtherForward(Long numberOfCellsToMoveForward){
        return board.getCellXPositionsFurtherForward(this, numberOfCellsToMoveForward);
    }

    public Cell getCellXPositionsFurtherBackward(Long numberOfCellsToGoesBack){
        return board.getCellXPositionsFurtherBackward(this,numberOfCellsToGoesBack);
    }

    public String getName(){
        return this.name;
    }

    public Boolean isOwneable(){
        return false;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void addPlayerToCell(Player player){
        this.playersOnCell.add(player);
    }

    public void removePlayerFromCell(Player player) {
        if(this.playersOnCell.contains(player)){
            this.playersOnCell.remove(player);
        }
    }

    public List<Player> getPlayersOnCell() {
        return this.playersOnCell;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
