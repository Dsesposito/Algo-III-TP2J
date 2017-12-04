package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Cells.*;
import fiuba.algo3.tp2.model.Exceptions.AlgoPolyNoActualTurnException;
import fiuba.algo3.tp2.model.Exceptions.AlgoPolyPlayerQuantityException;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class AlgoPoly {

    private static AlgoPoly instance = null;

    public enum ListenerTurnProperty {
        PLAYER,
        DICE
    }

    private Map<ListenerTurnProperty,List<PropertyChangeListener>> turnListeners;

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
        turnListeners = new HashMap<>();
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

    public void addPlayerToGame(String name, Token token){
        if(!isAbleToAddPlayer()){
            throw new AlgoPolyPlayerQuantityException("The maximium of three players have already been reached");
        }
        players.add(new Player(name,board.getStartCell(),token));

    }

    public void addPlayerToGame(String name){
        if(!isAbleToAddPlayer()){
            throw new AlgoPolyPlayerQuantityException("The maximium of three players have already been reached");
        }
        players.add(new Player(name,board.getStartCell(),new Token(this.board.getStartCell().getPosition())));

    }

    public void startGame(){
        if(players.size() < 2){
            throw new AlgoPolyPlayerQuantityException("In order to play is needed at least two players");
        }

        Collections.shuffle(players);

        Player firstPlayer = players.get(0);

        this.logEvent("El primer jugador en mover será " + firstPlayer.getName());
        firePlayerChangeEvent(null,firstPlayer);
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

        Player newPlayer = players.get(nextPlayerIndex);
        firePlayerChangeEvent(actualPlayer,newPlayer);

        this.actualTurn = new Turn(newPlayer);

        this.fireDiceThrownEvent("");

        this.logEvent("Turno del jugador " + this.getActualPlayer().getName());
    }

    public void throwDice(){
        actualTurn.setDiceResult(Long.valueOf(die1.throwDie()), Long.valueOf(die2.throwDie()));
        String value = actualTurn.getDiceResult().toString();
        this.fireDiceThrownEvent(value);
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

    public void mockThrowDice(Long face1, Long face2){
        actualTurn.setDiceResult(face1, face2);
    }

    public void movePlayer() {
        Player player = actualTurn.getCurrentPlayer();
        player.move(actualTurn);
    }

    public void logEvent(String message){
        this.console.addMessage(message);
    }

    public void playerHasBeenDefeated() {
        players.remove(this.getActualPlayer());
        this.nextTurn();
    }

    public Boolean allOponentsHasBeenDefeated() {
        return players.size() == 1;
    }

    public void playerPayBail() {
        this.board.getJail().playerPayBail(this.getActualPlayer());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPropertyChangeListener(ListenerTurnProperty type , PropertyChangeListener listener) {
        if(this.turnListeners.containsKey(type)){
            this.turnListeners.get(type).add(listener);
        }
        else{
            this.turnListeners.put(type,new ArrayList<>(Arrays.asList(listener)));
        }
    }

    private void firePlayerChangeEvent(Player oldPlayer,Player newPlayer){
        for(PropertyChangeListener listener : this.turnListeners.get(ListenerTurnProperty.PLAYER)){
            listener.propertyChange(new PropertyChangeEvent(this,ListenerTurnProperty.PLAYER.toString(),oldPlayer,newPlayer));
        }
    }


    private void fireDiceThrownEvent(String newValue) {
        for(PropertyChangeListener listener : this.turnListeners.get(ListenerTurnProperty.DICE)){
            listener.propertyChange(new PropertyChangeEvent(this,ListenerTurnProperty.PLAYER.toString(),"",newValue));
        }
    }
}
