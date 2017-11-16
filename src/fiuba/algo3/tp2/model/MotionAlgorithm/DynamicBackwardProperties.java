package fiuba.algo3.tp2.model.MotionAlgorithm;

import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Player;

public class DynamicBackwardProperties implements MotionAlgorithm {
    @Override
    public void move(Player player, Long diceResult) {
        Cell futureCell = player.getCurrentCell().moveBackwardXCells(player.getNumberOfProperties());
        player.goToCell(futureCell);
    }
}
