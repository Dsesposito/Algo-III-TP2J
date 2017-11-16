package fiuba.algo3.tp2.model;

public class DynamicForwardPlus2 implements MotionAlgorithm{

    @Override
    public void move(Player player, Long diceResult) {
        Cell futureCell = player.getCurrentCell().moveForwardXCells(diceResult-2);
        player.goToCell(futureCell);
    }
}
