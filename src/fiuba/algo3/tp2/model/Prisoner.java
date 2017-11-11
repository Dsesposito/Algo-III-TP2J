package fiuba.algo3.tp2.model;

public class Prisoner {

    private Player imprisonedPlayer;

    private Long numberOfTurns;

    private static Long maxNumberOfTurnsInJail = 3L;
    private static Long minNumberOfTurnsInJail = 1L;

    public Prisoner (Player player){
        imprisonedPlayer = player;
        numberOfTurns = 0L;
    }

    public Boolean isFreeToGo(){
        return numberOfTurns.equals(maxNumberOfTurnsInJail);
    }

    public Boolean isAbleToPayBail(){
        return (numberOfTurns > minNumberOfTurnsInJail);
    }

    public Boolean isPlayer(Player player){
        return this.imprisonedPlayer.equals(player);
    }

    public void incrementTurn(){
        numberOfTurns++;
    }

}
