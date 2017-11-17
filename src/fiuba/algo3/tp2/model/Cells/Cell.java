package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Board;

public class Cell{

    private String name;
    private Board board;

    public Cell(String name,Board board){
        this.name = name;
        this.board = board;

    }

    public Cell moveForwardXCells(Long numberOfCellsToMoveForward){
        return board.moveForwardXCells(this,numberOfCellsToMoveForward);
    }

    public Cell moveBackwardXCells(Long numberOfCellsToGoesBack){
        return board.moveBackwardXCells(this,numberOfCellsToGoesBack);
    }

    public Boolean isCell(String cellName){
        return this.getName().equals(cellName);
    }

    public String getName(){
        return this.name;
    }

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
}
