package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Player;

public class Neighborhood {

    private Player owner;

    private String name;

    private Long numberOfBuiltHouses;

    private Boolean hasHotelBuilt;

    public Neighborhood(String name){
        this.name = name;
        this.numberOfBuiltHouses = 0L;
        this.hasHotelBuilt = false;
    }

    public void buyHouse(){
        this.numberOfBuiltHouses++;
    }

    public void buyHotel(){
        this.numberOfBuiltHouses = 0L;
        this.hasHotelBuilt = true;
    }

    public Long getNumberOfHouses(){
        return numberOfBuiltHouses;
    }

    public Long getNumberOfHotel(){
        if (this.hasHotelBuilt){
            return 1L;
        }
        return 0L;
    }

    public void buy(Player player){
        this.owner = player;
        player.addNeighborhood(this);
    }

    public Boolean isOwnedBy(Player player){
        return player.equals(this.owner);
    }

}
