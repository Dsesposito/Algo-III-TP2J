package fiuba.algo3.tp2.model;

public class Neighborhood {

    private Player owner;

    private String name;

    public Neighborhood(String name){
        name = name;
    }

    public void buy(Player player){
        this.owner = player;
    }

    public Boolean isOwnedBy(Player player){
        return player.equals(this.owner);
    }

}
