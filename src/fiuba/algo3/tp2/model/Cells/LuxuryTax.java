package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Position;
import fiuba.algo3.tp2.model.Turn;

public class LuxuryTax extends Cell {
    public LuxuryTax(String name, Board board, Position boardPosition) {
        super(name, board, boardPosition);
    }

    private static Double taxComission = Global.config.getDouble("taxComission");

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        player.getCurrentCell().removePlayerFromCell(player);
        super.addPlayerToCell(player);
        player.landsOnLuxuryTax(this);
        player.decrementMoney(player.getMoney().multiply(taxComission));
    }
}