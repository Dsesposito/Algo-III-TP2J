package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cells.Neighborhood;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class NeighborhoodTest {
    private static final double DELTA = 1e-15;

    @Test
    public void test01BuyNeighborhoodHeMustBeTheOwner(){
        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        bsassur.buy(player1);
        Assert.assertTrue(bsassur.isOwnedBy(player1));
    }

    @Test
    public void test02BuyNeighborhoodHisMoneyMustBeReducedByTheSalePrice(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        double start_money = player1.getMoney().getValue();
        bsassur.buy(player1);
        double finish_money = player1.getMoney().getValue();
        Assert.assertTrue(bsassur.isOwnedBy(player1));
        Assert.assertEquals(finish_money, start_money-20000, DELTA);
    }

    @Test
    public void test03BuyBsAsSurAndBsAsNorteAndBuyAHouseAndTheMoneyDecrementBy5000(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        double start_money = player1.getMoney().getValue();
        bsassur.buyHouse();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-5000, DELTA);
    }

    @Test
    public void test04BuyBsAsSurAndBsAsNorteAndBuyAHouseAndTheMoneyDecrementBy5000(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        bsassur.buyHouse();
        bsasnorte.buyHouse();

        player2.landsOnNeighborhood(bsassur);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3000, DELTA);
    }

}

