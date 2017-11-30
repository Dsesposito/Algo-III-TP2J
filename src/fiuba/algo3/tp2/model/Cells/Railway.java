package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.*;

public class Railway extends Cell implements Groupable, Owneable {

    private Player owner;
    private Money businessPrice;
    private Double diceMultiplierSingle;
    private Double diceMultiplierInGroup;
    private Double actualDiceMultiplier;

    public Railway(String name, Board board, Money businessPrice, Double diceMultiplierSingle, Double diceMultiplierInGroup) {
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
                Railway railway = (Railway)groupable;
                railway.actualDiceMultiplier = railway.diceMultiplierInGroup;
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
    public Money getSaleValue() {
        //TODO Mover a archivo de configuracion
        double commission_of_sale = 1-0.15;

        return this.businessPrice.multiply(commission_of_sale);
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        player.landsOnRailWay(this);
        if(this.hasOwner() && !this.isOwnedBy(player)){
            Money rentalPrice = Money.withValue(actualTurn.getDiceResult() * actualDiceMultiplier);
            if(!player.hasEnoughMoney(rentalPrice)){

                if(player.sellingPropertiesHasEnoughMoney(rentalPrice)){
                    AlgoPoly.getInstance().logEvent("El jugador " + player.getName() + " no posee dinero suficiente para pagar el precio de alquiler. Para poder avanzar primero debe saldar su deuda de " + rentalPrice.toString());
                    player.createDeb(new Debt(player,this.owner,rentalPrice));
                }
                else{
                    AlgoPoly.getInstance().logEvent("El jugador " + player.getName() + " no posee suficiente propiedades para saldar su deuda de " + rentalPrice.toString() + ". El jugador ha si derrotado. ");
                    player.setDefeated();
                }

            }
            else {
                player.decrementMoney(rentalPrice);
                this.owner.incrementMoney(rentalPrice);
            }
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
