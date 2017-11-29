package fiuba.algo3.tp2.model.MotionAlgorithm;

import fiuba.algo3.tp2.model.Exceptions.PlayerExceptions.PlayerDefeatedException;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

public class Defeted implements MotionAlgorithm {
    @Override
    public void move(Player player, Turn turn) {
        throw new PlayerDefeatedException("Player can't move. First he must solves his debt.");
    }
}
