package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Cells.*;
import fiuba.algo3.tp2.model.Exceptions.AlgoPolyNoActualTurnException;
import fiuba.algo3.tp2.model.Exceptions.AlgoPolyPlayerQuantityException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoPoly {

    private static AlgoPoly instance = null;

    List<Player> players;
    Board board;
    Turn actualTurn;
    Die die1;
    Die die2;
    Console console;

    private static Long maxNumberOfPlayers = Global.config.getLong("maxNumberOfPlayers");

    private AlgoPoly(){
        players = new ArrayList<>();
        board = new Board();
        die1 = new Die();
        die2 = new Die();
        console = new Console();
    }

    public static AlgoPoly getInstance() {
        if(instance == null){
            instance = new AlgoPoly();
        }
        return instance;
    }


    public Boolean isAbleToAddPlayer(){
        return (getQuantityOfPlayers() < maxNumberOfPlayers);
    }

    public Integer getQuantityOfPlayers(){
        return players.size();
    }

    public void addPlayerToGame(String name){
        if(!isAbleToAddPlayer()){
            throw new AlgoPolyPlayerQuantityException("The maximium of three players have already been reached");
        }
        players.add(new Player(name,board.getStartCell()));

    }

    public void startGame(){
        if(players.size() < 2){
            throw new AlgoPolyPlayerQuantityException("In order to play is needed at least two players");
        }

        Collections.shuffle(players);

        Player firstPlayer = players.get(0);

        this.logEvent("El primer jugador en mover será " + firstPlayer.getName());

        this.actualTurn = new Turn(firstPlayer);

    }

    public void resetGame(){
        this.players = new ArrayList<>();
        this.actualTurn = null;
    }

    public void nextTurn(){
        Player actualPlayer = this.getActualPlayer();
        int nextPlayerIndex = players.indexOf(actualPlayer) + 1;
        if(nextPlayerIndex + 1 > players.size()){
            nextPlayerIndex = ((nextPlayerIndex + 1) % players.size()) - 1;
        }
        this.actualTurn = new Turn(players.get(nextPlayerIndex));

        this.logEvent("Turno del jugador " + this.getActualPlayer().getName());
    }

    public void throwDice(){
        Integer diceResult = die1.throwDie() + die2.throwDie();
        actualTurn.setDiceResult(Long.valueOf(diceResult));
    }

    public Board getBoard(){
        return this.board;
    }

    public Console getConsole(){
        return this.console;
    }

    public Player getActualPlayer(){

        if(this.actualTurn == null){
            throw new AlgoPolyNoActualTurnException("There is not a turn running");
        }

        return this.actualTurn.getCurrentPlayer();
    }

    public Turn getActualTurn(){
        return this.actualTurn;
    }

    public void movePlayer() {
        Player player = this.actualTurn.getCurrentPlayer();
        player.move(this.actualTurn);
    }

    public void logEvent(String message){
        this.console.addMessage(message);
    }
}
