package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.model.Exceptions.PlayerMovementInJailException;

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
    public void nextTurn(Player player) {
        jail.incrementTurn(player);
    }

    @Override
    public Boolean isInJail(Player player) {
        return true;
    }
}
