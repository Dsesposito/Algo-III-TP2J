package fiuba.algo3.tp2.model;

public class Turn {

    Player currentPlayer;
    Long diceResult;

    public Turn(Player player){
        currentPlayer=player;
    }

    public Long getDiceResult() {
        return diceResult;
    }

    public void mockDice(Long die1, Long die2) {
        this.diceResult = die1 + die2;
    }
}
