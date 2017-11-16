package fiuba.algo3.tp2.model.MotionAlgorithm;

import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Player;

public class DynamicForwardProperties implements MotionAlgorithm {
    @Override
    public void move(Player player, Long diceResult) {
        Cell futureCell = player.getCurrentCell().moveForwardXCells(player.getNumberOfProperties());
        player.goToCell(futureCell);
    }
}
