package fiuba.algo3.tp2;
import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cells.StartPoint;
import fiuba.algo3.tp2.model.Money;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Cells.Quini6;
import fiuba.algo3.tp2.model.Turn;
import org.junit.Assert;
import org.junit.Test;

public class Quini6Test {
    private static final double DELTA = 1e-15;
    @Test
    public void test01PlayerLandsOnQuini6OneTimeAndHisMoneyIsIncrementedBy50000() {

        Board board = new Board();
        Player player1 = new Player("lucas",board.getStartCell());

        Quini6 quini6 = board.getQuini6();

        Long face1 = 6L;
        Long face2 = 6L;

        Turn turn = new Turn(player1);
        turn.mockDice(face1,face2);

        quini6.playerLandsOnCell(player1,turn);

        Money money = player1.getMoney();

        Assert.assertEquals( 100000.0 + 50000.0, money.getValue(), DELTA);
    }

    @Test
    public void test02PlayerLandsOnQuini6OneTimeAndHisMoneyIsIncrementedBy30000() {

        Board board = new Board();
        Player player1 = new Player("lucas",board.getStartCell());

        Quini6 quini6 = board.getQuini6();

        Long face1 = 6L;
        Long face2 = 6L;

        Turn turn = new Turn(player1);
        turn.mockDice(face1,face2);

        quini6.playerLandsOnCell(player1,turn);
        quini6.playerLandsOnCell(player1,turn);

        Money money = player1.getMoney();

        Assert.assertEquals(100000.0 + 50000.0 + 30000.0, money.getValue(), DELTA);
    }

    @Test
    public void test03PlayerLandsOnQuini6OneTimeAndHisMoneyIsNotIncremented() {

        Board board = new Board();
        Player player1 = new Player("lucas",board.getStartCell());

        Quini6 quini6 = board.getQuini6();

        Long face1 = 6L;
        Long face2 = 6L;

        Turn turn = new Turn(player1);
        turn.mockDice(face1,face2);

        quini6.playerLandsOnCell(player1,turn);
        quini6.playerLandsOnCell(player1,turn);
        quini6.playerLandsOnCell(player1,turn);

        Money money = player1.getMoney();

        Assert.assertEquals(100000.0 + 50000.0 + 30000.0, money.getValue(), DELTA);
    }
}
