package fiuba.algo3.tp2;
import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cells.Police;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;
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

        Police police = board.getPolice();

        Long face1 = 6L;
        Long face2 = 6L;

        Turn turn = new Turn(player1);
        turn.mockDice(face1,face2);

        police.playerLandsOnCell(player1,turn);

        Assert.assertTrue(player1.isInCell(board.getJail()));

        player1.move(turn);

        Assert.assertTrue(player1.isInCell(board.getJail()));
    }

}
