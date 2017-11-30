package fiuba.algo3.tp2;


import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cells.Police;
import fiuba.algo3.tp2.model.Exceptions.InsufficientFundsException;
import fiuba.algo3.tp2.model.Cells.Jail;
import fiuba.algo3.tp2.model.Money;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JailTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test01PlayerInJailCanNotMoveInFirstTurn(){

        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());

        Police police = board.getPolice();

        Long face1 = 6L;
        Long face2 = 6L;

        Turn turn = new Turn(player1);
        turn.mockDice(face1,face2);

        police.playerLandsOnCell(player1,turn);

        player1.move(turn);

        Assert.assertTrue(player1.isInCell(board.getJail()));
    }

    @Test
    public void test02PlayerInJailPayBailAndThenMoves(){

        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());

        Jail jail = board.getJail();
        Police police = board.getPolice();

        Long face1 = 6L;
        Long face2 = 6L;

        Turn turn = new Turn(player1);
        turn.mockDice(face1,face2);

        police.playerLandsOnCell(player1,turn);

        player1.move(turn);
        player1.move(turn);

        Assert.assertTrue(jail.isAbleToPayBail(player1));

        jail.playerPayBail(player1);

        player1.move(turn);

        Assert.assertFalse(player1.isInCell(board.getJail()));
    }


    @Test
    public void test03PlayerTryPayBailButHeHasInsufficientFunds(){

        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());

        Money money = new Money(60000.0);
        player1.payToBank(money);

        Jail jail = board.getJail();
        Police police = board.getPolice();

        Long face1 = 6L;
        Long face2 = 6L;

        Turn turn = new Turn(player1);
        turn.mockDice(face1,face2);

        police.playerLandsOnCell(player1,turn);

        player1.move(turn);
        player1.move(turn);

        thrown.expect(InsufficientFundsException.class);

        jail.playerPayBail(player1);
    }

    @Test
    public void test04PlayerWaitsForHisReleaseAndMoves(){
        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());

        Money money = new Money(60000.0);
        player1.payToBank(money);

        Jail jail = board.getJail();
        Police police = board.getPolice();
        Long face1 = 6L;
        Long face2 = 6L;

        Turn turn = new Turn(player1);
        turn.mockDice(face1,face2);

        police.playerLandsOnCell(player1,turn);
        player1.move(turn);
        player1.move(turn);
        player1.move(turn);
        player1.move(turn);

        Assert.assertFalse(player1.isInCell(board.getJail()));
    }

}
