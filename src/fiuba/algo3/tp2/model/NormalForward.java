package fiuba.algo3.tp2.model;

public class NormalForward implements MotionAlgorithm {

    @Override
    public void move(Player player, Long diceResult) {
        Cell futureCell = player.getCurrentCell().moveForwardXCells(diceResult);
        player.goToCell(futureCell);
    }
}
