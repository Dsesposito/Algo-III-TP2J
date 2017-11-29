package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cells.Neighborhood;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;
import org.junit.Assert;
import org.junit.Test;

public class PropertiesTest {
    private static final double DELTA = 1e-15;

    @Test
    public void test01ExchangePropertieAndAnotherPlayerLandsThereAndTheNewOwnerIncreasesHisMoney(){
        Board board = new Board();
        Player player1 = new Player("Lucas", board.getStartCell());
        Player player2 = new Player("Diego", board.getStartCell());
        Player player3 = new Player("Guido", board.getStartCell());
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        bsassur.buyHouse();
        bsassur.buyHouse();

        bsassur.sell();
        double money_player1_start = player1.getMoney().getValue();
        bsassur.buy(player2);
        Assert.assertTrue(bsassur.isOwnedBy(player2));

        double money_player2_start = player2.getMoney().getValue();
        Turn turn = new Turn(player3);
        bsassur.playerLandsOnCell(player3, turn);
        double money_player2_finish = player2.getMoney().getValue();
        double money_player1_finish = player1.getMoney().getValue();

        Assert.assertEquals(money_player1_finish, money_player1_start, DELTA);
        Assert.assertEquals(money_player2_finish, money_player2_start + bsassur.getRentalPrice().getValue(), DELTA);

    }

    @Test
    public void test02ExchangePropertieAndAnotherPlayerLandsThereAndTheNewOwnerIncreasesHisMoneyByRentPriceWithoutBuildings(){
        Board board = new Board();
        Player player1 = new Player("Lucas", board.getStartCell());
        Player player2 = new Player("Diego", board.getStartCell());
        Player player3 = new Player("Guido", board.getStartCell());
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        bsassur.buyHouse();
        // Only one house
        bsassur.sell();
        bsassur.buy(player2);

        double money_player2_start = player2.getMoney().getValue();
        Turn turn = new Turn(player3);
        bsassur.playerLandsOnCell(player3, turn);
        double money_player2_finish = player2.getMoney().getValue();

        Assert.assertEquals(money_player2_finish, money_player2_start + 2000, DELTA);

        bsassur.sell();
        bsassur.buy(player1);
        bsassur.buyHouse();
        bsassur.buyHouse();
        // Two houses
        bsassur.sell();
        bsassur.buy(player2);

        money_player2_start = player2.getMoney().getValue();
        bsassur.playerLandsOnCell(player3, turn);
        money_player2_finish = player2.getMoney().getValue();

        Assert.assertEquals(money_player2_finish, money_player2_start + 2000, DELTA);

        bsassur.sell();
        bsassur.buy(player1);
        bsassur.buyHouse();
        bsassur.buyHouse();
        bsasnorte.buyHouse();
        bsasnorte.buyHouse();
        bsassur.buyHotel();
        //With Hotel
        bsassur.sell();
        bsassur.buy(player2);

        money_player2_start = player2.getMoney().getValue();
        bsassur.playerLandsOnCell(player3, turn);
        money_player2_finish = player2.getMoney().getValue();

        Assert.assertEquals(money_player2_finish, money_player2_start + 2000, DELTA);

    }


}
