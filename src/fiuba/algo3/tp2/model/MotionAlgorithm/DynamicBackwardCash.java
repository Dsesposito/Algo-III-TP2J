package fiuba.algo3.tp2.model.MotionAlgorithm;

import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Player;

public class DynamicBackwardCash implements MotionAlgorithm {
    @Override
    public void move(Player player, Long diceResult) {
        Long numberOfMoves = (long) Math.floor(player.getMoney().modulus(diceResult));
        Cell futureCell = player.getCurrentCell().moveBackwardXCells(numberOfMoves);
        player.goToCell(futureCell);
    }
}
