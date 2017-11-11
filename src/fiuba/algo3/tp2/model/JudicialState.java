package fiuba.algo3.tp2.model;

public interface JudicialState {

    public void moveFoward(Player player);

    public void nextTurn(Player player);

    public Boolean isInJail(Player player);
}
