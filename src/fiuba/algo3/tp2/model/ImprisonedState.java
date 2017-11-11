package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.model.Exceptions.PlayerMovementInJailException;

public class ImprisonedState implements JudicialState {

    @Override
    public void moveFoward(Player player) {
        throw new PlayerMovementInJailException("Player " + player.getName() + " can't move because is in jail");
    }
}
