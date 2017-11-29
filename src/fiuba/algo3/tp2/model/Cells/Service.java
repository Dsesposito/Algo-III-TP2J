package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.*;

public class Service extends Cell implements Groupable , Owneable {

    private Player owner;
    private Money businessPrice;
    private Double diceMultiplierSingle;
    private Double diceMultiplierInGroup;
    private Double actualDiceMultiplier;

    public Service(String name, Board board, Money businessPrice, Double diceMultiplierSingle, Double diceMultiplierInGroup) {
        super(name, board);
        this.businessPrice = businessPrice;
        this.diceMultiplierSingle = diceMultiplierSingle;
        this.diceMultiplierInGroup = diceMultiplierInGroup;
        this.actualDiceMultiplier = diceMultiplierSingle;
    }

    public void buy(Player player){
        player.decrementMoney(businessPrice);
        this.owner = player;
        this.owner.addOwneable(this);

        AlgoPoly.getInstance().logEvent("El jugador " + player.getName() + " compró " + super.getName());

        if(super.cellGroupHasSameOwner(player)){
            for(Groupable groupable : super.getGroup().getGroupables()){
                Service service = (Service)groupable;
                service.actualDiceMultiplier = service.diceMultiplierInGroup;
            }
        }
    }

    @Override
    public void sell(){
        //TODO Mover a archivo de configuracion
        double commission_of_sale = 1-0.15;

        this.owner.incrementMoney(this.businessPrice.multiply(commission_of_sale));

        AlgoPoly.getInstance().logEvent("El jugador " + owner.getName() + " vendió " + super.getName());

        this.owner.dropOwneable(this);
        this.owner = null;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public Boolean isNeighborhood() {
        return false;
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        player.landsOnService(this);
        if(this.hasOwner() && !this.isOwnedBy(player)){
            Money moneyToDecrement = Money.withValue(actualTurn.getDiceResult() * actualDiceMultiplier);
            player.decrementMoney(moneyToDecrement);
        }
    }

    public Boolean isOwnedBy(Player player){
        return player.equals(this.owner);
    }

    public Double getDiceMultiplier() {
        return this.actualDiceMultiplier;
    }

    @Override
    public Boolean isOwneable(){
        return true;
    }

    @Override
    public Boolean hasOwner() {
        return this.owner != null;
    }
}
