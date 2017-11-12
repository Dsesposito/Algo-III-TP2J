package fiuba.algo3.tp2;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Quini6;
import org.junit.Assert;
import org.junit.Test;

public class Quini6Test {
    private static final double DELTA = 1e-15;
    @Test
    public void test01PlayerLandsOnQuini6OneTimeAndHisMoneyIsIncrementedBy50000() {
        Player player1 = new Player("lucas");
        double money = player1.getMoney();
        Quini6 quini6 = new Quini6();
        quini6.newWinner(player1);
        Assert.assertEquals(money + 50000, player1.getMoney(), DELTA);
    }

    @Test
    public void test02PlayerLandsOnQuini6OneTimeAndHisMoneyIsIncrementedBy30000() {
        Player player1 = new Player("lucas");
        double money = player1.getMoney();
        Quini6 quini6 = new Quini6();
        quini6.newWinner(player1);
        quini6.newWinner(player1);
        Assert.assertEquals(money + 50000 + 30000, player1.getMoney(), DELTA);
    }

    @Test
    public void test03PlayerLandsOnQuini6OneTimeAndHisMoneyIsNotIncremented() {
        Player player1 = new Player("lucas");
        double money = player1.getMoney();
        Quini6 quini6 = new Quini6();
        quini6.newWinner(player1);
        quini6.newWinner(player1);
        quini6.newWinner(player1);
        Assert.assertEquals(money + 50000 + 30000, player1.getMoney(), DELTA);

        quini6.newWinner(player1);
        quini6.newWinner(player1);
        Assert.assertEquals(money + 50000 + 30000, player1.getMoney(), DELTA);
    }
}
