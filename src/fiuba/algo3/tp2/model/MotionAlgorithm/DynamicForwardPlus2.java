package fiuba.algo3.tp2.model.MotionAlgorithm;

import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

public class DynamicForwardPlus2 implements MotionAlgorithm{

    @Override
    public void move(Player player, Turn turn) {
        Cell futureCell = player.getCurrentCell().getCellXPositionsFurtherForward(turn.getDiceResult()-2);
        futureCell.playerLandsOnCell(player,turn);
    }
}
