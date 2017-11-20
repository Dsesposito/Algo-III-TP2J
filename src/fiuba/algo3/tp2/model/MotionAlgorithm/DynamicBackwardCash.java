package fiuba.algo3.tp2.model.MotionAlgorithm;

import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

public class DynamicBackwardCash implements MotionAlgorithm {
    @Override
    public void move(Player player, Turn turn) {
        Long numberOfMoves = (long) Math.floor(player.getMoney().modulus(turn.getDiceResult()));
        Cell futureCell = player.getCurrentCell().getCellXPositionsFurtherBackward(numberOfMoves);
        futureCell.playerLandsOnCell(player,turn);
    }
}
