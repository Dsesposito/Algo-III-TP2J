package fiuba.algo3.tp2.model.JudicialState;

import fiuba.algo3.tp2.model.Exceptions.PlayerActionInJailException;
import fiuba.algo3.tp2.model.Exceptions.PlayerMovementInJailException;
import fiuba.algo3.tp2.model.Cells.Jail;
import fiuba.algo3.tp2.model.Player;

public class ImprisonedState implements JudicialState {

    private Jail jail;

    public ImprisonedState(Jail jail){
        this.jail = jail;
    }

    @Override
    public void moveFoward(Player player) {
        throw new PlayerMovementInJailException("Player " + player.getName() + " can't move because is in jail");
    }
    @Override
    public void doAction(Player player){
        throw new PlayerActionInJailException("Player " + player.getName() + " can't do any action because is in jail");
    }


    @Override
    public void nextTurn(Player player) {
        jail.incrementTurn(player);
    }

    @Override
    public Boolean isInJail(Player player) {
        return true;
    }
}
