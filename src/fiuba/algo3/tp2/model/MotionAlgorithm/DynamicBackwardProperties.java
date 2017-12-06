package fiuba.algo3.tp2.model.MotionAlgorithm;

import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

public class DynamicBackwardProperties implements MotionAlgorithm {
    @Override
    public void move(Player player, Turn turn) {

        Long positionsToMove = player.getNumberOfProperties();
        if(positionsToMove == 0){
            positionsToMove++;
        }

        Cell futureCell = player.getCurrentCell().getCellXPositionsFurtherBackward(positionsToMove);
        futureCell.playerLandsOnCell(player,turn);
    }
}
