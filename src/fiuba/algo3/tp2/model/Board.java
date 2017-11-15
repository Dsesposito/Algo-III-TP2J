package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Exceptions.BoardPlayerQuantityException;

import java.util.ArrayList;
import java.util.List;

public class Board {

    List<Cell> cells;
    List<Player> players;

    private static Long maxNumberOfPlayersOnBoard = Global.config.getLong("maxNumberOfPlayersOnBoard");

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

    public Boolean isAbleToAddPlayer(){
        return (getQuantityOfPlayers() <= maxNumberOfPlayersOnBoard);
    }

    public Integer getQuantityOfPlayers(){
        return players.size();
    }

    public void addPlayerToBoard(Player player){
        if(!isAbleToAddPlayer()){
            throw new BoardPlayerQuantityException("The maximium of three players have already been reached");
        }
        players.add(player);

    }
}
