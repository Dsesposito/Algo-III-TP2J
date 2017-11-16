package fiuba.algo3.tp2.model.JudicialState;

import fiuba.algo3.tp2.model.Player;

public interface JudicialState {

    public void moveFoward(Player player);

    public void nextTurn(Player player);

    public Boolean isInJail(Player player);

    public void doAction(Player player);
}
