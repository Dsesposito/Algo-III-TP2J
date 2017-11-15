package fiuba.algo3.tp2;
import fiuba.algo3.tp2.model.Exceptions.PlayerActionInJailException;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Jail;
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
        Jail jail = new Jail();
        player1.goToJail(jail);
        thrown.expect(PlayerActionInJailException.class);
        player1.doAction();
    }

    @Test
    public void test02LandsOnPoliceAndTheNewPositionIsJail() {
        Player player1 = new Player("Lucas");
        Jail jail = new Jail();
        player1.goToJail(jail);
        Assert.assertEquals("Carcel", player1.getCell().getName());
    }


}
