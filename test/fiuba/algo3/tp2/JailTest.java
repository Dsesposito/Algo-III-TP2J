package fiuba.algo3.tp2;


import fiuba.algo3.tp2.model.Exceptions.PlayerActionInJailException;
import fiuba.algo3.tp2.model.Exceptions.InsufficientFundsException;
import fiuba.algo3.tp2.model.Exceptions.PlayerMovementInJailException;
import fiuba.algo3.tp2.model.Jail;
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

        Player player1 = new Player("Diego");

        Jail jail = new Jail();
        player1.goToJail(jail);

        thrown.expect(PlayerMovementInJailException.class);

        player1.moveFoward();
    }

    @Test
    public void test02PlayerInJailPayBailAndThenMoves(){

        Player player1 = new Player("Diego");

        Jail jail = new Jail();
        jail.addPrisoner(player1);

        player1.nextTurn();
        player1.nextTurn();

        Assert.assertTrue(jail.isAbleToPayBail(player1));

        jail.playerPayBail(player1);

        player1.moveFoward();
    }


    @Test
    public void test03PlayerTryPayBailButHeHasInsufficientFunds(){

        Player player1 = new Player("Diego");

        Money money = new Money(60000.0);
        player1.payToBank(money);

        Jail jail = new Jail();
        jail.addPrisoner(player1);

        player1.nextTurn();
        player1.nextTurn();

        thrown.expect(InsufficientFundsException.class);

        jail.playerPayBail(player1);
    }

    @Test
    public void test03LandsOnPoliceAndCanNotDoActions() {
        Player player1 = new Player("Lucas");
        Jail jail = new Jail();
        player1.goToJail(jail);
        thrown.expect(PlayerActionInJailException.class);
        player1.doAction();
    }

}
