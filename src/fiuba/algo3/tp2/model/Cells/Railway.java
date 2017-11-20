package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

public class Railway extends Cell {
    public Railway(String name, Board board) {
        super(name, board);
    }

    @Override
    public void playerLandsOnCell(Player player,Turn actualTurn) {
        player.landsOnRailWay(this);
    }
}
