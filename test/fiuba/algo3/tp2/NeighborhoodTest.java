package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Neighborhood;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;

public class NeighborhoodTest {

    public void testBuyNeighborhoodHeMustBeTheOwner(){
        Player player1 = new Player("Diego");
        Neighborhood bsassur = new Neighborhood("Buenos Aires - Sur");
        bsassur.buy(player1);
        Assert.assertTrue(bsassur.isOwnedBy(player1));
    }

}
