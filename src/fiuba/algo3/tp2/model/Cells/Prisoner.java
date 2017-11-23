package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Player;

public class Prisoner {

    private Player imprisonedPlayer;

    private Long numberOfTurns;

    private static Long maxNumberOfTurnsInJail = Global.config.getLong("maxNumberOfTurnsInJail");
    private static Long minNumberOfTurnsInJail = Global.config.getLong("minNumberOfTurnsInJail");

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prisoner prisoner = (Prisoner) o;

        return imprisonedPlayer != null ? imprisonedPlayer.equals(prisoner.imprisonedPlayer) : prisoner.imprisonedPlayer == null;
    }

    @Override
    public int hashCode() {
        return imprisonedPlayer != null ? imprisonedPlayer.hashCode() : 0;
    }

}
