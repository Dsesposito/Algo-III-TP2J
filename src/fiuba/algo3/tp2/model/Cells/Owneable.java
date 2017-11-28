package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Player;

public interface Owneable {

    Boolean hasOwner();

    void buy(Player player);

    Player getOwner();
}
