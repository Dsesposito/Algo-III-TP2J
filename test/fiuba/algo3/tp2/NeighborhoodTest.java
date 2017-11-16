package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Cells.Neighborhood;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class NeighborhoodTest {

    @Test
    public void test01BuyNeighborhoodHeMustBeTheOwner(){
        Player player1 = new Player("Diego");
        Neighborhood bsassur = new Neighborhood("Buenos Aires - Sur");
        bsassur.buy(player1);
        Assert.assertTrue(bsassur.isOwnedBy(player1));
    }

}
