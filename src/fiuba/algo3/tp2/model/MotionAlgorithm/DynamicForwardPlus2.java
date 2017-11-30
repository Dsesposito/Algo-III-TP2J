package fiuba.algo3.tp2.model.MotionAlgorithm;

import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

public class DynamicForwardPlus2 implements MotionAlgorithm{

    @Override
    public void move(Player player, Turn turn) {
        Long positionsToMove = turn.getDiceResult()-2;
        if(positionsToMove == 0){
            positionsToMove++;
        }
        Cell futureCell = player.getCurrentCell().getCellXPositionsFurtherForward(positionsToMove);
        futureCell.playerLandsOnCell(player,turn);
    }
}
