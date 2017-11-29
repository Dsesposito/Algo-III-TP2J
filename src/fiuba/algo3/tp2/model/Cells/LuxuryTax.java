package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

public class LuxuryTax extends Cell {
    public LuxuryTax(String name, Board board) {
        super(name, board);
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        double tax = 0.15;
        player.landsOnLuxuryTax(this);
        player.decrementMoney(player.getMoney().multiply(tax));
    }
}
