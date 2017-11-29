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
            AlgoPoly.getInstance().logEvent("La propiedad le pertenece a " + this.owner.getName());

            if(!player.hasEnoughMoney(this.getRentalPrice())){
                AlgoPoly.getInstance().logEvent("El jugador " + player.getName() + " no posee dinero suficiente para pagar el precio de alquiler. Para poder avanzar primero debe saldar su deuda de " + this.getRentalPrice().toString());
                player.createDeb(new Debt(player,this.owner,this.getRentalPrice()));
            }
            else {
                player.decrementMoney(this.getRentalPrice());
                this.owner.incrementMoney(this.getRentalPrice());
            }
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

            AlgoPoly.getInstance().logEvent("El jugador " + this.owner.getName() + " compró una casa en el barrio " + this.name);
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

            AlgoPoly.getInstance().logEvent("El jugador " + this.owner.getName() + " compró un hotel en el barrio " + this.name);

        }

    }


    public void buy(Player player){

        if(this.owner != null && this.owner != player){
            throw new NeighborhoodWithOwnerException("The neighborhood already has an owner");
        }

        AlgoPoly.getInstance().logEvent("El jugador " + player.getName() + " compró el barrio " + this.name);

        this.owner = player;
        this.owner.addOwneable(this);
        this.owner.decrementMoney(this.getLandPrice());
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public Boolean isNeighborhood() {
        return true;
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

    @Override
    public void sell(){
        //TODO Mover a archivo de configuracion
        double commission_of_sale = 1-0.15;

        this.owner.incrementMoney(this.getLandPrice().multiply(commission_of_sale));
        if(this.rent.hastHotelBuilt()){
            this.owner.incrementMoney(hotelPrice.multiply(commission_of_sale));
        }
        else if(this.rent.getNumberOfBuiltHouses() > 0){
            this.owner.incrementMoney(housePrice.multiply(this.rent.getNumberOfBuiltHouses()*commission_of_sale));
        }

        AlgoPoly.getInstance().logEvent("El jugador " + owner.getName() + " vendió el barrio " + this.name);

        this.rent.clearBuiltHousesAndHotels();
        this.owner.dropOwneable(this);
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

    public Boolean hasHotelBuilt(){
        if(this.rent.hastHotelBuilt()){
            return true;
        }
        return false;
    }

    @Override
    public Boolean isOwneable(){
        return true;
    }

}
