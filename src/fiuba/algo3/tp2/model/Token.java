package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.model.Cells.Cell;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Token {

    private IntegerProperty xpos;
    private IntegerProperty ypos;
    private Position previousPosition;
    private Color color;
    private Player player;

    public Token() {
        this.xpos = new SimpleIntegerProperty(0);
        this.ypos = new SimpleIntegerProperty(0);
        previousPosition = Position.inXY(xpos.get(),ypos.get());
        this.color = Color.rgb(getRandomColor(),getRandomColor(),getRandomColor());
    }

    public Token(Position position){
        this.xpos = new SimpleIntegerProperty(position.getXpos());
        this.ypos = new SimpleIntegerProperty(position.getYpos());
        previousPosition = Position.inXY(xpos.get(),ypos.get());
        this.color = Color.rgb(getRandomColor(),getRandomColor(),getRandomColor());
    }

    public void addPositionLisetener(ChangeListener listener){
        this.xpos.addListener(listener);
        this.ypos.addListener(listener);
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void updatePlayersOnCellPosition(){

        Map<Player,Position> playersPosition = distributePlayersInCell();

        for(Map.Entry<Player,Position> entry : playersPosition.entrySet()){
            entry.getKey().getToken().updatePosition(entry.getValue());
        }
    }

    public Map<Player,Position> distributePlayersInCell(){

        Cell cell = this.getPlayer().getCurrentCell();
        Position cellPosition = cell.getPosition();
        List<Player> playersOnCell = cell.getPlayersOnCell();
        Map<Player,Position> playersPosition = new HashMap<>();
        if(playersOnCell.size() == 1){
            playersPosition.put(playersOnCell.get(0),new Position(cellPosition));
        }
        else if(playersOnCell.size() == 2){
            Position player0Pos = new Position(cellPosition.getXpos(),cellPosition.getYpos() - 20);
            Position player1Pos = new Position(cellPosition.getXpos(),cellPosition.getYpos() + 20);
            playersPosition.put(playersOnCell.get(0),player0Pos);
            playersPosition.put(playersOnCell.get(1),player1Pos);
        }
        else if(playersOnCell.size() == 3){
            Position player0Pos = new Position(cellPosition.getXpos(),cellPosition.getYpos() - 20);
            Position player1Pos = new Position(cellPosition.getXpos(),cellPosition.getYpos() + 20);
            Position player2Pos = new Position(cellPosition.getXpos() - 25,cellPosition.getYpos());
            playersPosition.put(playersOnCell.get(0),player0Pos);
            playersPosition.put(playersOnCell.get(1),player1Pos);
            playersPosition.put(playersOnCell.get(2),player2Pos);
        }
        return playersPosition;
    }

    private void updatePosition(Position position) {
        previousPosition = Position.inXY(xpos.get(),ypos.get());
        xpos.set(position.getXpos());
        ypos.set(position.getYpos());
    }

    private Integer getRandomColor(){
        return Math.toIntExact(Math.round(Math.random()*255));
    }

    public Color getColor() {
        return color;
    }

    public Player getPlayer() {
        return player;
    }

    public Position getPosition() {
        return Position.inXY(this.xpos.get(),this.ypos.get());
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }
}
