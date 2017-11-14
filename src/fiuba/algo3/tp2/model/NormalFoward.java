package fiuba.algo3.tp2.model;

public class NormalFoward implements MotionAlgorithm {

    @Override
    public void move(Player player, Long diceResult) {
        Cell futureCell = player.getCurrentCell().moveFowardXCells(diceResult);
        player.goToCell(futureCell);
    }
}
