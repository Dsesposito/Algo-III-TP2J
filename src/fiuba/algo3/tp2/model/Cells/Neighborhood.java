package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.*;
import fiuba.algo3.tp2.model.Exceptions.NeighborhoodWithOutOwnerException;
import fiuba.algo3.tp2.model.Exceptions.NeighborhoodZoneWithOutSameOwnerException;

public class Neighborhood extends Cell {

    private Player owner;

    private String name;

    private Long numberOfBuiltHouses;

    private Boolean hasHotelBuilt;

    private Money landPrice;

    private Money housePrice;

    private Money hotelPrice;

    private NeighborhoodZone zone;

    public Neighborhood(String name,Money landPrice,Money housePrice,Money hotelPrice,Board board){
        super(name, board);
        this.name = name;
        this.numberOfBuiltHouses = 0L;
        this.hasHotelBuilt = false;
        this.landPrice = landPrice;
        this.hotelPrice = hotelPrice;
        this.housePrice = housePrice;
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        player.landsOnNeighborhood(this);
    }

    public void buyHouse(){
        if(owner == null){
            throw new NeighborhoodWithOutOwnerException("The neighborhood must have an owner before a house can be bought");
        }
        if(!zone.hasSameOwner(owner)){
            throw new NeighborhoodZoneWithOutSameOwnerException("The neighborhood zone must have the same owner before a house can be bought");
        }
        this.numberOfBuiltHouses++;
        owner.decrementMoney(housePrice);
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

    public void setZone(NeighborhoodZone zone){
        this.zone = zone;
    }

    public Money getLandPrice() {
        return landPrice;
    }

    public Money getHousePrice() {
        return housePrice;
    }

    public Money getHotelPrice() {
        return hotelPrice;
    }
}
