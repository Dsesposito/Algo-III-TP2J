package fiuba.algo3.tp2.model.MotionAlgorithm;

import fiuba.algo3.tp2.model.Cells.Jail;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

public class StoppedInJail implements MotionAlgorithm {

    Jail jail;

    public StoppedInJail(Jail jail){
        this.jail = jail;
    }

    @Override
    public void move(Player player, Turn turn) {
        if(jail.isFreeToGo(player)){
            new NormalForward().move(player,turn);
        }
        else{
            jail.incrementTurn(player);
        }
    }
}
