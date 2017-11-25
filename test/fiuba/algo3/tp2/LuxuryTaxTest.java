package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cells.LuxuryTax;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;
import org.junit.Assert;
import org.junit.Test;

public class LuxuryTaxTest {
    private static final double DELTA = 1e-15;

    @Test
    public void test01PlayerLandsOnLuxuryTaxCellAndHisMoneyDecrementBy15Percent(){
        Board board = new Board();
        Player player1 = new Player("Lucas", board.getStartCell());
        LuxuryTax tax = board.getLuxuryTax();

        Turn turn = new Turn(player1);
        double start_money = player1.getMoney().getValue();
        tax.playerLandsOnCell(player1, turn);
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money - start_money*0.15, DELTA);

    }

}
