package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.*;
import fiuba.algo3.tp2.model.Exceptions.NeighborhoodWithOutOwnerException;
import fiuba.algo3.tp2.model.Exceptions.NeighborhoodZoneWithOutSameOwnerException;

public class Neighborhood extends Cell implements Purchasable{

    private Player owner;

    private String name;

    private Long numberOfBuiltHouses;

    private Long numberOfBuiltHotels;

    private Boolean hasHotelBuilt;

    private Money landPrice;

    private Money housePrice;

    private Money hotelPrice;

    private Rental rent;

    private Long maxHouses;

    private boolean completeHouses;

    public Neighborhood(String name,Money landPrice,Money housePrice,Money hotelPrice, Rental rent, Long maxHouses, Board board){
        super(name, board);
        this.name = name;
        this.numberOfBuiltHouses = 0L;
        this.numberOfBuiltHotels = 0L;
        this.hasHotelBuilt = false;
        this.landPrice = landPrice;
        this.hotelPrice = hotelPrice;
        this.housePrice = housePrice;
        this.rent = rent;
        this.maxHouses = maxHouses;
        this.completeHouses = false;
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        player.landsOnNeighborhood(this);
        if(this.hasOwner()){
            player.decrementMoney(this.getRentalPrice());
            owner.incrementMoney(this.getRentalPrice());
        }
    }

    public void buyHouse(){
        if(owner == null){
            throw new NeighborhoodWithOutOwnerException("The neighborhood must have an owner before a house can be bought");
        }
        if(!super.cellGroupHasSameOwner(owner)){
            throw new NeighborhoodZoneWithOutSameOwnerException("The neighborhood zone must have the same owner before a house can be bought");
        }

        if(!completeHouses){
            numberOfBuiltHouses++;
            owner.decrementMoney(housePrice);
            rent.updateRentalPriceDueHouses(numberOfBuiltHouses - 1);
            if(numberOfBuiltHouses.equals(maxHouses)){
                completeHouses = true;
            }
        }

    }

    public void buyHotel(){
        if(super.cellGroupHasSameOwner(owner) & super.cellGroupHasCompleteHouses() & !hasHotelBuilt){
            numberOfBuiltHouses = 0L;
            hasHotelBuilt = true;
            numberOfBuiltHotels++;
            owner.decrementMoney(hotelPrice);
            rent.updateRentalPriceDueHotels(numberOfBuiltHotels-1);
        }
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
        player.getMoney().subtract(this.getLandPrice());
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

    public Boolean hasCompleteHouses(){
        return completeHouses;
    }

    public void sellPropertie(){
        double commission_of_sale = 1-0.15;

        owner.getMoney().add(this.getLandPrice().getValue()*commission_of_sale);
        if(hasHotelBuilt){
            owner.getMoney().add(hotelPrice.getValue()*commission_of_sale);
        }
        else{
            owner.getMoney().add(housePrice.getValue()*numberOfBuiltHouses*commission_of_sale);
        }

        numberOfBuiltHouses = 0L;
        hasHotelBuilt = false;
        rent.updateRentalPriceWithotBuildings();
        completeHouses = false;
        owner.dropNeighborhood(this);
        owner = null;
    }

    public Boolean hasOwner(){
        return !(this.owner == null) ;
    }

}
