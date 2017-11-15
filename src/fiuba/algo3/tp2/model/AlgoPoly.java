package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Exceptions.AlgoPolyPlayerQuantityException;

import java.util.ArrayList;
import java.util.List;

public class AlgoPoly {

    List<Player> players;
    Board board;

    private static Long maxNumberOfPlayers = Global.config.getLong("maxNumberOfPlayers");

    public AlgoPoly() {
        players = new ArrayList<>();
        board = new Board();
    }

    public Boolean isAbleToAddPlayer(){
        return (getQuantityOfPlayers() <= maxNumberOfPlayers);
    }

    public Integer getQuantityOfPlayers(){
        return players.size();
    }

    public void addPlayerToGame(Player player){
        if(!isAbleToAddPlayer()){
            throw new AlgoPolyPlayerQuantityException("The maximium of three players have already been reached");
        }
        players.add(player);

    }
}
