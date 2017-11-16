package fiuba.algo3.tp2.model;

public class DynamicForwardProperties implements MotionAlgorithm {
    @Override
    public void move(Player player, Long diceResult) {
        Cell futureCell = player.getCurrentCell().moveForwardXCells(player.getNumberOfProperties());
        player.goToCell(futureCell);
    }
}
