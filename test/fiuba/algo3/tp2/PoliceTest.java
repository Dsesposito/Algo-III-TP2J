package fiuba.algo3.tp2;
import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Exceptions.PlayerActionInJailException;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Cells.Jail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class PoliceTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test01LandsOnPoliceAndCanNotDoActions() {
        Board board = new Board();
        Player player1 = new Player("Lucas",board.getStartCell());
        Jail jail = board.getJail();
        player1.goToJail(jail);
        thrown.expect(PlayerActionInJailException.class);
        player1.doAction();
    }

    @Test
    public void test02LandsOnPoliceAndTheNewPositionIsJail() {
        Board board = new Board();
        Player player1 = new Player("Lucas",board.getStartCell());
        Jail jail = board.getJail();
        player1.goToJail(jail);
        Assert.assertTrue(player1.isInJail());
    }


}
