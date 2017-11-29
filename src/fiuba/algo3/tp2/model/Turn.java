package fiuba.algo3.tp2.model;

public class Turn {

    private Player currentPlayer;
    private Long die1Result;
    private Long die2Result;
    private boolean playAgain;

    public Turn(Player player){
        currentPlayer=player;
        playAgain = false;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void setDiceResult(Long die1Result, Long die2Result){
        this.die1Result = die1Result;
        this.die2Result = die2Result;
        playAgain = die1Result.equals(die2Result) && !playAgain;
    }

    public Long getDiceResult() {
        return die1Result + die2Result;
    }

    public void mockDice(Long die1, Long die2) {
        this.die1Result = die1;
        this.die2Result = die2;
        playAgain = die1Result.equals(die2Result) && !playAgain;
    }
    public boolean playAgain(){
        return playAgain;
    }
}
