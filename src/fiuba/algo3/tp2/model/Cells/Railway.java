package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Money;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

public class Railway extends Cell implements Groupable {

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
        if(super.cellGroupHasSameOwner(player)){
            for(Groupable groupable : super.getGroup().getGroupables()){
                Railway railway = (Railway)groupable;
                railway.actualDiceMultiplier = railway.diceMultiplierInGroup;
            }
        }
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        player.landsOnRailWay(this);
        if(!this.isOwnedBy(player)){
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
}
