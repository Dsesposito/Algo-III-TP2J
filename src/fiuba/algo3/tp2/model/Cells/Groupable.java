package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Player;

public interface Groupable {

    Boolean isOwnedBy(Player player);
    void groupBy(CellGroup cellGroup);
}
