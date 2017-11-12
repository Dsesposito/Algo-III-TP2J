package fiuba.algo3.tp2;
import fiuba.algo3.tp2.model.Exceptions.PlayerActionInJailException;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class PoliceTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test01LandsOnPoliceAndCanNotDoActions() {
        Player player1 = new Player("Lucas");
        player1.goToJail();
        thrown.expect(PlayerActionInJailException.class);
        player1.doAction();
    }

    @Test
    public void test02LandsOnPoliceAndTheNewPositionIsJail() {
        Player player1 = new Player("Lucas");
        player1.goToJail();
        Assert.assertEquals("Jail", player1.getCell().getName());
    }


}
