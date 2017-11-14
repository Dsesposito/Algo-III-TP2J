package fiuba.algo3.tp2.model;

public class DynamicFowardPlus2 implements MotionAlgorithm{

    @Override
    public void move(Player player, Long diceResult) {
        Cell futureCell = player.getCurrentCell().moveFowardXCells(diceResult-2);
        player.goToCell(futureCell);
    }
}
