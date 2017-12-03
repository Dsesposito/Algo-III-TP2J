package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Position;
import fiuba.algo3.tp2.model.Turn;

public class DynamicForward extends Cell{
    public DynamicForward(String name, Board board, Position boardPosition) {
        super(name, board, boardPosition);
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        player.getCurrentCell().removePlayerFromCell(player);
        super.addPlayerToCell(player);
        player.landsOnDynamicForward(this,actualTurn);
    }
}
