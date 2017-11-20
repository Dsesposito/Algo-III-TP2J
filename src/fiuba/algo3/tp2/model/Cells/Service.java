package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Money;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

public class Service extends Cell {

    private Player owner;
    private Money businessPrice;
    private Double diceMultiplier;

    public Service(String name, Board board, Money businessPrice, Double diceMultiplier) {
        super(name, board);
        this.businessPrice = businessPrice;
        this.diceMultiplier = diceMultiplier;
    }

    public void buy(Player player){
        player.decrementMoney(businessPrice);
        this.owner = player;
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        player.landsOnService(this);

        Money moneyToDecrement = Money.withValue(actualTurn.getDiceResult() * diceMultiplier);
        player.decrementMoney(moneyToDecrement);
    }
}
