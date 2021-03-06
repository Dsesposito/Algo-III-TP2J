package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.Global;
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

    private static Double commissionOfSale = Global.config.getDouble("commissionOfSale");

    public Neighborhood(String name,Money landPrice,Money housePrice,Money hotelPrice, Rental rent, Long maxHouses, Board board, Position boardPosition){
        super(name, board, boardPosition);
        this.name = name;
        this.landPrice = landPrice;
        this.hotelPrice = hotelPrice;
        this.housePrice = housePrice;
        this.rent = rent;
        this.maxHouses = maxHouses;
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        player.getCurrentCell().removePlayerFromCell(player);
        super.addPlayerToCell(player);
        player.landsOnNeighborhood(this);
        if(this.hasOwner() && !this.owner.equals(player)){
            AlgoPoly.getInstance().logEvent("La propiedad le pertenece a " + this.owner.getName());

            if(!player.hasEnoughMoney(this.getRentalPrice())){

                if(player.sellingPropertiesHasEnoughMoney(this.getRentalPrice())){
                    AlgoPoly.getInstance().logEvent("El jugador " + player.getName() + " no posee dinero suficiente para pagar el precio de alquiler. Para poder avanzar primero debe saldar su deuda de " + this.getRentalPrice().toString());
                    player.createDebt(new Debt(player,this.owner,this.getRentalPrice()));
                }
                else{
                    AlgoPoly.getInstance().logEvent("El jugador " + player.getName() + " no posee suficiente propiedades para saldar su deuda de " + this.getRentalPrice().toString() + ". El jugador ha si derrotado. ");
                    player.setDefeated();
                }
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


        this.rent.incrementBuiltHouses();
        this.owner.decrementMoney(this.housePrice);

        AlgoPoly.getInstance().logEvent("El jugador " + this.owner.getName() + " compró una casa en el barrio " + this.name);
    }

    public void buyHotel(){

        if(!((NeighborhoodZone) super.getGroup()).hasCompleteHouses()) {
            throw new NeighborhoodWithOutAllHousesBuiltException("The neighborhood must have all houses built before a hotel can be built");
        }

        if(this.rent.hastHotelBuilt()){
            throw new NeighborhoodWithHotelAlreadyBuiltException("The neighborhood can't have multiples hotels");
        }

        this.rent.clearBuiltHouses();
        this.rent.incrementBuiltHotels();
        this.owner.decrementMoney(hotelPrice);

        AlgoPoly.getInstance().logEvent("El jugador " + this.owner.getName() + " compró un hotel en el barrio " + this.name);
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

    @Override
    public Money getSaleValue() {
        Money saleValue = this.getLandPrice().multiply(commissionOfSale);
        if(this.rent.hastHotelBuilt()){
            saleValue = saleValue.add(hotelPrice.multiply(commissionOfSale));
        }
        else if(this.rent.getNumberOfBuiltHouses() > 0){
            saleValue = saleValue.add(housePrice.multiply(this.rent.getNumberOfBuiltHouses()*commissionOfSale));
        }
        return saleValue;
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

        this.owner.incrementMoney(this.getLandPrice().multiply(commissionOfSale));
        if(this.rent.hastHotelBuilt()){
            this.owner.incrementMoney(hotelPrice.multiply(commissionOfSale));
        }
        else if(this.rent.getNumberOfBuiltHouses() > 0){
            this.owner.incrementMoney(housePrice.multiply(this.rent.getNumberOfBuiltHouses()*commissionOfSale));
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

    public Integer getNumberOfBuiltHouses() {
        return Math.toIntExact(this.rent.getNumberOfBuiltHouses());
    }

    public Integer getNumberOfBuiltHotels() {
        return Math.toIntExact(this.rent.getNumberOfBuiltHotels());
    }
}
