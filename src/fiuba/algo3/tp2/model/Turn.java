package fiuba.algo3.tp2.model;

public class Turn {

    private Player currentPlayer;
    private Long diceResult;

    public Turn(Player player){
        currentPlayer=player;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void setDiceResult(Long diceResult){
        this.diceResult = diceResult;
    }

    public Long getDiceResult() {
        return diceResult;
    }

    public void mockDice(Long die1, Long die2) {
        this.diceResult = die1 + die2;
    }
}
