package fiuba.algo3.tp2.model.MotionAlgorithm;

import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Player;

public class DynamicBackwardPlus2 implements MotionAlgorithm {
    @Override
    public void move(Player player, Long diceResult) {
        Cell futureCell = player.getCurrentCell().moveBackwardXCells(diceResult-2);
        player.goToCell(futureCell);
    }
}
