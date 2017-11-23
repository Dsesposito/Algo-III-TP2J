package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Player;

import java.util.List;

public class CellGroup {

    private List<Cell> cells;

    private String name;

    private CellGroup(String name, List<Cell> cells){
        this.cells = cells;
        this.name = name;
        for(Cell cell : this.cells){
            cell.groupBy(this);
        }
    }

    public Boolean isOwnedBySamePlayer(Player player){
        for(Cell cell : cells){
            if(!((Purchasable)cell).isOwnedBy(player)){
                return false;
            }
        }
        return true;
    }

    public Boolean hasCompleteHouses(){
        for(Cell cell : cells){
            if(!((Neighborhood)cell).hasAllHousesBuilt()){
                return false;
            }
        }
        return true;
    }

    public static void groupCells(String name, List<Cell> cells) {
        new CellGroup(name,cells);
    }

    public List<Cell> getCells(){
        return this.cells;
    }
}
