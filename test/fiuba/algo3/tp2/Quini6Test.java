package fiuba.algo3.tp2;
import fiuba.algo3.tp2.model.Money;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Quini6;
import org.junit.Assert;
import org.junit.Test;

public class Quini6Test {
    private static final double DELTA = 1e-15;
    @Test
    public void test01PlayerLandsOnQuini6OneTimeAndHisMoneyIsIncrementedBy50000() {

        Player player1 = new Player("lucas");

        Quini6 quini6 = new Quini6();
        quini6.newWinner(player1);

        Money money = player1.getMoney();

        Assert.assertEquals( 100000.0 + 50000.0, money.getValue(), DELTA);
    }

    @Test
    public void test02PlayerLandsOnQuini6OneTimeAndHisMoneyIsIncrementedBy30000() {

        Player player1 = new Player("lucas");

        Quini6 quini6 = new Quini6();
        quini6.newWinner(player1);
        quini6.newWinner(player1);

        Money money = player1.getMoney();

        Assert.assertEquals(100000.0 + 50000.0 + 30000.0, money.getValue(), DELTA);
    }

    @Test
    public void test03PlayerLandsOnQuini6OneTimeAndHisMoneyIsNotIncremented() {

        Player player1 = new Player("lucas");

        Quini6 quini6 = new Quini6();
        quini6.newWinner(player1);
        quini6.newWinner(player1);
        quini6.newWinner(player1);

        Money money = player1.getMoney();

        Assert.assertEquals(100000.0 + 50000.0 + 30000.0, money.getValue(), DELTA);

        quini6.newWinner(player1);
        quini6.newWinner(player1);

        Assert.assertEquals(100000.0 + 50000.0 + 30000.0, money.getValue(), DELTA);
    }
}
