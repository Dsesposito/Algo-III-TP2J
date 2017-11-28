package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.*;
import fiuba.algo3.tp2.model.Exceptions.NeighborhoodExceptions.*;

public class Neighborhood extends Cell implements Groupable , Owneable{

    private Player owner;

    private String name;

    private Money landPrice;

    private Money housePrice;

    private Money hotelPrice;

    private Rental rent;

    private Long maxHouses;

    public Neighborhood(String name,Money landPrice,Money housePrice,Money hotelPrice, Rental rent, Long maxHouses, Board board){
        super(name, board);
        this.name = name;
        this.landPrice = landPrice;
        this.hotelPrice = hotelPrice;
        this.housePrice = housePrice;
        this.rent = rent;
        this.maxHouses = maxHouses;
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        player.landsOnNeighborhood(this);
        if(this.hasOwner() && !this.owner.equals(player)){
            player.decrementMoney(this.getRentalPrice());
            this.owner.incrementMoney(this.getRentalPrice());
        }
    }

    public void buyHouse(){
        if(this.owner == null){
            throw new NeighborhoodWithOutOwnerException("The neighborhood must have an owner before a house can be bought");
        }
        if(!super.cellGroupHasSameOwner(this.owner)){
            throw new NeighborhoodZoneWithOutSameOwnerException("The neighborhood zone must have the same owner before a house can be bought");
        }

        if(this.hasAllHousesBuilt()){
            throw new NeighborhoodFullHousesException("The neighborhood has all houses already built");
        }

        if(!this.hasAllHousesBuilt()){
            this.rent.incrementBuiltHouses();
            this.owner.decrementMoney(this.housePrice);
        }

    }

    public void buyHotel(){

        /*
        if(!super.cellGroupHasCompleteHouses()) {
            throw new NeighborhoodWithOutAllHousesBuiltException("The neighborhood must have all houses built before a hotel can be built");
        }
        */

        if(this.rent.hastHotelBuilt()){
            throw new NeighborhoodWithHotelAlreadyBuiltException("The neighborhood can't have multiples hotels");
        }

        if( ((NeighborhoodZone) super.getGroup()).hasCompleteHouses() ) {
            this.rent.clearBuiltHouses();
            this.rent.incrementBuiltHotels();
            this.owner.decrementMoney(hotelPrice);
        }

    }


    public void buy(Player player){

        if(this.owner != null && this.owner != player){
            throw new NeighborhoodWithOwnerException("The neighborhood already has an owner");
        }

        this.owner = player;
        this.owner.addNeighborhood(this);
        this.owner.decrementMoney(this.getLandPrice());
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    public Boolean isOwnedBy(Player player){
        return player.equals(this.owner);
    }

    public Money getLandPrice() {
        return landPrice;
    }

    public Money getRentalPrice() {
        return rent.getRentalPrice();
    }

    public void sellPropertie(){
        //TODO Mover a archivo de configuracion
        double commission_of_sale = 1-0.15;

        this.owner.incrementMoney(this.getLandPrice().multiply(commission_of_sale));
        if(this.rent.hastHotelBuilt()){
            this.owner.incrementMoney(hotelPrice.multiply(commission_of_sale));
        }
        else{
            this.owner.incrementMoney(housePrice.multiply(this.rent.getNumberOfBuiltHouses()*commission_of_sale));
        }

        this.rent.clearBuiltHousesAndHotels();
        this.owner.dropNeighborhood(this);
        this.owner = null;
    }

    public Boolean hasOwner(){
        return !(this.owner == null) ;
    }

    public Long getNumberOfHotelAndHouses(){
        return rent.getNumberOfBuiltHouses() + rent.getNumberOfBuiltHotels();
    }

    public Boolean hasAllHousesBuilt(){
        if(this.rent.hastHotelBuilt()){
            return true;
        }
        else{
            return this.rent.getNumberOfBuiltHouses().equals(this.maxHouses);
        }

    }

    @Override
    public Boolean isOwneable(){
        return true;
    }

}
