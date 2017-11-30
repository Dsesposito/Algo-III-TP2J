package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

public class Police extends Cell {

    Jail jail;

    public Police(String name, Board board) {
        super(name, board);
        jail = board.getJail();
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        jail.addPrisoner(player);
        player.landsOnPolice(this,jail);
    }
}
