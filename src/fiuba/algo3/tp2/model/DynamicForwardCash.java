package fiuba.algo3.tp2.model;

public class DynamicForwardCash implements MotionAlgorithm {
    @Override
    public void move(Player player, Long diceResult) {
        Long numberOfMoves = (long) Math.floor(player.getMoney().modulus(diceResult));
        Cell futureCell = player.getCurrentCell().moveForwardXCells(numberOfMoves);
        player.goToCell(futureCell);
    }
}
