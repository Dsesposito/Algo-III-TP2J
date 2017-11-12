package fiuba.algo3.tp2.model;

public class Player {

    private String name;

    private JudicialState judicialState;

    public Player(String name){
        this.name = name;
        this.judicialState = new FreeState();
    }

    public void goToJail(Jail jail){
        this.judicialState = new ImprisonedState(jail);
    }

    public void releasedFromJail(){
        this.judicialState = new FreeState();
    }

    public Boolean isInJail(){
        return judicialState.isInJail(this);
    }

    public void moveFoward(){
        judicialState.moveFoward(this);
    }

    public void nextTurn(){
        judicialState.nextTurn(this);
    }

    public String getName(){
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return name != null ? name.equals(player.name) : player.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}