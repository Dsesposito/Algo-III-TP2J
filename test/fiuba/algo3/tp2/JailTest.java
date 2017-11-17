package fiuba.algo3.tp2;


import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Exceptions.InsufficientFundsException;
import fiuba.algo3.tp2.model.Exceptions.PlayerMovementInJailException;
import fiuba.algo3.tp2.model.Cells.Jail;
import fiuba.algo3.tp2.model.Money;
import fiuba.algo3.tp2.model.Player;
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

        Jail jail = board.getJail();
        player1.goToJail(jail);

        thrown.expect(PlayerMovementInJailException.class);

        player1.moveFoward();
    }

    @Test
    public void test02PlayerInJailPayBailAndThenMoves(){

        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());

        Jail jail = board.getJail();
        jail.addPrisoner(player1);

        player1.nextTurn();
        player1.nextTurn();

        Assert.assertTrue(jail.isAbleToPayBail(player1));

        jail.playerPayBail(player1);

        player1.moveFoward();
    }


    @Test
    public void test03PlayerTryPayBailButHeHasInsufficientFunds(){

        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());

        Money money = new Money(60000.0);
        player1.payToBank(money);

        Jail jail = board.getJail();
        jail.addPrisoner(player1);

        player1.nextTurn();
        player1.nextTurn();

        thrown.expect(InsufficientFundsException.class);

        jail.playerPayBail(player1);
    }

}
