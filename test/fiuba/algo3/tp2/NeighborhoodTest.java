package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cells.Neighborhood;
import fiuba.algo3.tp2.model.Exceptions.NeighborhoodExceptions.NeighborhoodWithOutAllHousesBuiltException;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;
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
    public void test03BuyBsAsSurAndBsAsNorteAndBuyAHouseInBsAsSurAndTheMoneyDecrementBy5000(){
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
    public void test04BuyBsAsSurAndBsAsNorteAndBuyAHouseInBsAsNorteAndTheMoneyDecrementBy5500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        double start_money = player1.getMoney().getValue();
        bsasnorte.buyHouse();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-5500, DELTA);
    }

    @Test
    public void test05BuyBsAsSurAndBsAsNorteAndBuyAHouseInBsAsSurAndOpponentLandsThereAndHisMoneyDecrementBy3000(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        bsassur.buyHouse();

        Turn turn = new Turn(player2);
        bsassur.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3000, DELTA);
    }

    @Test
    public void test06BuyBsAsSurAndBsAsNorteAndBuyAHouseInBsAsNorteAndOpponentLandsThereAndHisMoneyDecrementBy3500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        bsasnorte.buyHouse();

        Turn turn = new Turn(player2);
        bsasnorte.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3500, DELTA);
    }


    @Test
    public void test07BuyBsAsSurAndBsAsNorteAndBuyTwoHousesInBsAsSurAndOpponentLandsThereAndHisMoneyDecrementBy3500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        bsassur.buyHouse();
        bsassur.buyHouse();
        Turn turn = new Turn(player2);
        bsassur.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3500, DELTA);

    }

    @Test
    public void test08BuyBsAsSurAndBsAsNorteAndBuyTwoHousesInBsAsNorteAndOpponentLandsThereAndHisMoneyDecrementBy4000(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        bsasnorte.buyHouse();
        bsasnorte.buyHouse();
        Turn turn = new Turn(player2);
        bsasnorte.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-4000, DELTA);

    }

    @Test
    public void test09BuyBsAsSurAndBsAsNorteAndHaveNotCompleteAllHousesAndBuyAHotelAndHisMoneyNotDecrement(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        bsassur.buyHouse();
        bsassur.buyHouse();
        bsasnorte.buyHouse();

        double start_money = player1.getMoney().getValue();
        try{
            bsassur.buyHotel();
            bsasnorte.buyHotel();
            Assert.assertFalse(true);
        }
        catch (NeighborhoodWithOutAllHousesBuiltException e){
            double finish_money = player1.getMoney().getValue();
            Assert.assertEquals(finish_money, start_money, DELTA);
        }
    }

    @Test
    public void test10BuyBsAsSurAndBsAsNorteAndHaveCompleteAllHousesAndBuyAHotelInBsAsSurAndHisMoneyDecrementBy8000(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        bsassur.buyHouse();
        bsassur.buyHouse();
        bsasnorte.buyHouse();
        bsasnorte.buyHouse();
        double start_money = player1.getMoney().getValue();
        bsassur.buyHotel();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-8000, DELTA);
    }

    @Test
    public void test11BuyBsAsSurAndBsAsNorteAndHaveCompleteAllHousesAndBuyAHotelInBsAsNorteAndHisMoneyDecrementBy9000(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        bsassur.buyHouse();
        bsassur.buyHouse();
        bsasnorte.buyHouse();
        bsasnorte.buyHouse();
        double start_money = player1.getMoney().getValue();
        bsasnorte.buyHotel();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-9000, DELTA);
    }

    @Test
    public void test12BuyBsAsSurAndBsAsNorteAndHaveCompleteAllHousesAndBuyAHotelInBsAsSurAndOpponentLandsThereAndHisMoneyDecrementBy5000() {
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        bsassur.buyHouse();
        bsassur.buyHouse();
        bsasnorte.buyHouse();
        bsasnorte.buyHouse();
        bsassur.buyHotel();

        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Turn turn = new Turn(player2);
        bsassur.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-5000, DELTA);
    }

    @Test
    public void test13BuyBsAsSurAndBsAsNorteAndHaveCompleteAllHousesAndBuyAHotelInBsAsNorteAndOpponentLandsThereAndHisMoneyDecrementBy6000() {
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);
        bsassur.buyHouse();
        bsassur.buyHouse();
        bsasnorte.buyHouse();
        bsasnorte.buyHouse();
        bsasnorte.buyHotel();

        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Turn turn = new Turn(player2);
        bsasnorte.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-6000, DELTA);
    }

    @Test
    public void test14BuyCordobaSurAndCordobaNorteAndBuyAHouseInCordobaSurAndTheMoneyDecrementBy2000(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood cordobasur = board.getNeighborhoodByName("Cordoba - Sur");
        Neighborhood cordobanorte = board.getNeighborhoodByName("Cordoba - Norte");
        cordobasur.buy(player1);
        cordobanorte.buy(player1);
        double start_money = player1.getMoney().getValue();
        cordobasur.buyHouse();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-2000, DELTA);
    }

    @Test
    public void test15BuyCordobaSurAndCordobaNorteAndBuyAHouseInCordobaNorteAndTheMoneyDecrementBy2200(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood cordobasur = board.getNeighborhoodByName("Cordoba - Sur");
        Neighborhood cordobanorte = board.getNeighborhoodByName("Cordoba - Norte");
        cordobasur.buy(player1);
        cordobanorte.buy(player1);
        double start_money = player1.getMoney().getValue();
        cordobanorte.buyHouse();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-2200, DELTA);
    }

    @Test
    public void test16BuyCordobaSurAndCordobaNorteAndBuyAHouseInCordobaSurAndOpponentLandsThereAndHisMoneyDecrementBy1500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood cordobasur = board.getNeighborhoodByName("Cordoba - Sur");
        Neighborhood cordobanorte = board.getNeighborhoodByName("Cordoba - Norte");
        cordobasur.buy(player1);
        cordobanorte.buy(player1);
        cordobasur.buyHouse();

        Turn turn = new Turn(player2);
        cordobasur.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-1500, DELTA);
    }

    @Test
    public void test17BuyCordobaSurAndCordobaNorteAndBuyAHouseInCordobaNorteAndOpponentLandsThereAndHisMoneyDecrementBy1800(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood cordobasur = board.getNeighborhoodByName("Cordoba - Sur");
        Neighborhood cordobanorte = board.getNeighborhoodByName("Cordoba - Norte");
        cordobasur.buy(player1);
        cordobanorte.buy(player1);
        cordobanorte.buyHouse();

        Turn turn = new Turn(player2);
        cordobanorte.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-1800, DELTA);
    }


    @Test
    public void test18BuyCordobaSurAndCordobaNorteAndBuyTwoHousesInCordobaSurAndOpponentLandsThereAndHisMoneyDecrementBy2500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood cordobasur = board.getNeighborhoodByName("Cordoba - Sur");
        Neighborhood cordobanorte = board.getNeighborhoodByName("Cordoba - Norte");
        cordobasur.buy(player1);
        cordobanorte.buy(player1);
        cordobasur.buyHouse();
        cordobasur.buyHouse();
        Turn turn = new Turn(player2);
        cordobasur.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-2500, DELTA);
    }

    @Test
    public void test19BuyCordobaSurAndCordobaNorteAndBuyTwoHousesInCordobaNorteAndOpponentLandsThereAndHisMoneyDecrementBy2900(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood cordobasur = board.getNeighborhoodByName("Cordoba - Sur");
        Neighborhood cordobanorte = board.getNeighborhoodByName("Cordoba - Norte");
        cordobasur.buy(player1);
        cordobanorte.buy(player1);
        cordobanorte.buyHouse();
        cordobanorte.buyHouse();
        Turn turn = new Turn(player2);
        cordobanorte.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-2900, DELTA);
    }

    @Test
    public void test20BuyCordobaSurAndCordobaNorteAndHaveNotCompleteAllHousesAndBuyAHotelAndHisMoneyNotDecrement(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood cordobasur = board.getNeighborhoodByName("Cordoba - Sur");
        Neighborhood cordobanorte = board.getNeighborhoodByName("Cordoba - Norte");
        cordobasur.buy(player1);
        cordobanorte.buy(player1);
        cordobasur.buyHouse();
        cordobasur.buyHouse();
        cordobanorte.buyHouse();

        double start_money = player1.getMoney().getValue();
        try{
            cordobasur.buyHotel();
            cordobanorte.buyHotel();
            Assert.assertFalse(true);
        }
        catch (NeighborhoodWithOutAllHousesBuiltException e){
            double finish_money = player1.getMoney().getValue();
            Assert.assertEquals(finish_money, start_money, DELTA);
        }
    }

    @Test
    public void test21BuyCordobaSurAndCordobaNorteAndHaveCompleteAllHousesAndBuyAHotelInCordobaSurAndHisMoneyDecrementBy3000(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood cordobasur = board.getNeighborhoodByName("Cordoba - Sur");
        Neighborhood cordobanorte = board.getNeighborhoodByName("Cordoba - Norte");
        cordobasur.buy(player1);
        cordobanorte.buy(player1);
        cordobasur.buyHouse();
        cordobasur.buyHouse();
        cordobanorte.buyHouse();
        cordobanorte.buyHouse();
        double start_money = player1.getMoney().getValue();
        cordobasur.buyHotel();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3000, DELTA);
    }

    @Test
    public void test22BuyCordobaSurAndCordobaNorteAndHaveCompleteAllHousesAndBuyAHotelInCordobaNorteAndHisMoneyDecrementBy3500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood cordobasur = board.getNeighborhoodByName("Cordoba - Sur");
        Neighborhood cordobanorte = board.getNeighborhoodByName("Cordoba - Norte");
        cordobasur.buy(player1);
        cordobanorte.buy(player1);
        cordobasur.buyHouse();
        cordobasur.buyHouse();
        cordobanorte.buyHouse();
        cordobanorte.buyHouse();
        double start_money = player1.getMoney().getValue();
        cordobanorte.buyHotel();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3500, DELTA);
    }

    @Test
    public void test23BuyCordobaSurAndCordobaNorteAndHaveCompleteAllHousesAndBuyAHotelInCordobaSurAndOpponentLandsThereAndHisMoneyDecrementBy3000() {
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood cordobasur = board.getNeighborhoodByName("Cordoba - Sur");
        Neighborhood cordobanorte = board.getNeighborhoodByName("Cordoba - Norte");
        cordobasur.buy(player1);
        cordobanorte.buy(player1);
        cordobasur.buyHouse();
        cordobasur.buyHouse();
        cordobanorte.buyHouse();
        cordobanorte.buyHouse();
        cordobasur.buyHotel();

        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Turn turn = new Turn(player2);
        cordobasur.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3000, DELTA);
    }

    @Test
    public void test24BuyCordobaSurAndCordobaNorteAndHaveCompleteAllHousesAndBuyAHotelInCordobaNorteAndOpponentLandsThereAndHisMoneyDecrementBy3500() {
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood cordobasur = board.getNeighborhoodByName("Cordoba - Sur");
        Neighborhood cordobanorte = board.getNeighborhoodByName("Cordoba - Norte");
        cordobasur.buy(player1);
        cordobanorte.buy(player1);
        cordobasur.buyHouse();
        cordobasur.buyHouse();
        cordobanorte.buyHouse();
        cordobanorte.buyHouse();
        cordobanorte.buyHotel();

        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Turn turn = new Turn(player2);
        cordobanorte.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3500, DELTA);
    }

    @Test
    public void test25BuySaltaSurAndSaltaNorteAndBuyAHouseInSaltaSurAndTheMoneyDecrementBy4500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood saltasur = board.getNeighborhoodByName("Salta - Sur");
        Neighborhood saltanorte = board.getNeighborhoodByName("Salta - Norte");
        saltasur.buy(player1);
        saltanorte.buy(player1);
        double start_money = player1.getMoney().getValue();
        saltasur.buyHouse();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-4500, DELTA);
    }

    @Test
    public void test26BuySaltaSurAndSaltaNorteAndBuyAHouseInSaltaNorteAndTheMoneyDecrementBy4500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood saltasur = board.getNeighborhoodByName("Salta - Sur");
        Neighborhood saltanorte = board.getNeighborhoodByName("Salta - Norte");
        saltasur.buy(player1);
        saltanorte.buy(player1);
        double start_money = player1.getMoney().getValue();
        saltanorte.buyHouse();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-4500, DELTA);
    }

    @Test
    public void test27BuySaltaSurAndSaltaNorteAndBuyAHouseInSaltaSurAndOpponentLandsThereAndHisMoneyDecrementBy3250(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood saltasur = board.getNeighborhoodByName("Salta - Sur");
        Neighborhood saltanorte = board.getNeighborhoodByName("Salta - Norte");
        saltasur.buy(player1);
        saltanorte.buy(player1);
        saltasur.buyHouse();

        Turn turn = new Turn(player2);
        saltasur.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3250, DELTA);
    }

    @Test
    public void test28BuySaltaSurAndSaltaNorteAndBuyAHouseInSaltaNorteAndOpponentLandsThereAndHisMoneyDecrementBy3250(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood saltasur = board.getNeighborhoodByName("Salta - Sur");
        Neighborhood saltanorte = board.getNeighborhoodByName("Salta - Norte");
        saltasur.buy(player1);
        saltanorte.buy(player1);
        saltanorte.buyHouse();

        Turn turn = new Turn(player2);
        saltanorte.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3250, DELTA);
    }


    @Test
    public void test29BuySaltaSurAndSaltaNorteAndBuyTwoHousesInSaltaSurAndOpponentLandsThereAndHisMoneyDecrementBy3850(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood saltasur = board.getNeighborhoodByName("Salta - Sur");
        Neighborhood saltanorte = board.getNeighborhoodByName("Salta - Norte");
        saltasur.buy(player1);
        saltanorte.buy(player1);
        saltasur.buyHouse();
        saltasur.buyHouse();
        Turn turn = new Turn(player2);
        saltasur.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3850, DELTA);
    }

    @Test
    public void test30BuySaltaSurAndSaltaNorteAndBuyTwoHousesInSaltaNorteAndOpponentLandsThereAndHisMoneyDecrementBy3850(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Neighborhood saltasur = board.getNeighborhoodByName("Salta - Sur");
        Neighborhood saltanorte = board.getNeighborhoodByName("Salta - Norte");
        saltasur.buy(player1);
        saltanorte.buy(player1);
        saltanorte.buyHouse();
        saltanorte.buyHouse();
        Turn turn = new Turn(player2);
        saltanorte.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3850, DELTA);
    }

    @Test
    public void test31BuySaltaSurAndSaltaNorteAndHaveNotCompleteAllHousesAndBuyAHotelAndHisMoneyNotDecrement(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood saltasur = board.getNeighborhoodByName("Salta - Sur");
        Neighborhood saltanorte = board.getNeighborhoodByName("Salta - Norte");
        saltasur.buy(player1);
        saltanorte.buy(player1);
        saltasur.buyHouse();
        saltasur.buyHouse();
        saltanorte.buyHouse();

        double start_money = player1.getMoney().getValue();
        try{
            saltasur.buyHotel();
            saltanorte.buyHotel();
            Assert.assertFalse(true);
        }
        catch (NeighborhoodWithOutAllHousesBuiltException e){
            double finish_money = player1.getMoney().getValue();
            Assert.assertEquals(finish_money, start_money, DELTA);
        }
    }

    @Test
    public void test32BuySaltaSurAndSaltaNorteAndHaveCompleteAllHousesAndBuyAHotelInSaltaSurAndHisMoneyDecrementBy7500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood saltasur = board.getNeighborhoodByName("Salta - Sur");
        Neighborhood saltanorte = board.getNeighborhoodByName("Salta - Norte");
        saltasur.buy(player1);
        saltanorte.buy(player1);
        saltasur.buyHouse();
        saltasur.buyHouse();
        saltanorte.buyHouse();
        saltanorte.buyHouse();
        double start_money = player1.getMoney().getValue();
        saltasur.buyHotel();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-7500, DELTA);
    }

    @Test
    public void test33BuySaltaSurAndSaltaNorteAndHaveCompleteAllHousesAndBuyAHotelInSaltaNorteAndHisMoneyDecrementBy7500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood saltasur = board.getNeighborhoodByName("Salta - Sur");
        Neighborhood saltanorte = board.getNeighborhoodByName("Salta - Norte");
        saltasur.buy(player1);
        saltanorte.buy(player1);
        saltasur.buyHouse();
        saltasur.buyHouse();
        saltanorte.buyHouse();
        saltanorte.buyHouse();
        double start_money = player1.getMoney().getValue();
        saltanorte.buyHotel();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-7500, DELTA);
    }

    @Test
    public void test34BuySaltaSurAndSaltaNorteAndHaveCompleteAllHousesAndBuyAHotelInSaltaSurAndOpponentLandsThereAndHisMoneyDecrementBy5500() {
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood saltasur = board.getNeighborhoodByName("Salta - Sur");
        Neighborhood saltanorte = board.getNeighborhoodByName("Salta - Norte");
        saltasur.buy(player1);
        saltanorte.buy(player1);
        saltasur.buyHouse();
        saltasur.buyHouse();
        saltanorte.buyHouse();
        saltanorte.buyHouse();
        saltasur.buyHotel();

        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Turn turn = new Turn(player2);
        saltasur.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-5500, DELTA);
    }

    @Test
    public void test35BuySaltaSurAndSaltaNorteAndHaveCompleteAllHousesAndBuyAHotelInSaltaNorteAndOpponentLandsThereAndHisMoneyDecrementBy5500() {
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood saltasur = board.getNeighborhoodByName("Salta - Sur");
        Neighborhood saltanorte = board.getNeighborhoodByName("Salta - Norte");
        saltasur.buy(player1);
        saltanorte.buy(player1);
        saltasur.buyHouse();
        saltasur.buyHouse();
        saltanorte.buyHouse();
        saltanorte.buyHouse();
        saltanorte.buyHotel();

        Player player2 = new Player("Lucas", board.getStartCell());
        double start_money = player2.getMoney().getValue();
        Turn turn = new Turn(player2);
        saltanorte.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-5500, DELTA);
    }

    @Test
    public void test36BuySantaFeAndOpponentLandsHereAndHisMoneyDecrementBy1500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        Neighborhood santafe = board.getNeighborhoodByName("Santa Fe");
        santafe.buy(player1);
        Turn turn = new Turn(player2);
        double start_money = player2.getMoney().getValue();
        santafe.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-1500, DELTA);
    }

    @Test
    public void test37BuySantaFeAndBuyAHouseAndHisMoneyDecrementBy4000(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood santafe = board.getNeighborhoodByName("Santa Fe");
        santafe.buy(player1);
        double start_money = player1.getMoney().getValue();
        santafe.buyHouse();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-4000, DELTA);
    }

    @Test
    public void test38BuySantaFeAndBuyAHouseAndOpponentLandsThereAndHisMoneyDecrementBy3500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        Neighborhood santafe = board.getNeighborhoodByName("Santa Fe");
        santafe.buy(player1);
        santafe.buyHouse();

        Turn turn = new Turn(player2);
        double start_money = player2.getMoney().getValue();
        santafe.playerLandsOnCell(player2, turn);

        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3500, DELTA);
    }

    @Test
    public void test39BuyNeuquenAndOpponentLandsHereAndHisMoneyDecrementBy1800(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        Neighborhood neuquen = board.getNeighborhoodByName("Neuquén");
        neuquen.buy(player1);
        Turn turn = new Turn(player2);
        double start_money = player2.getMoney().getValue();
        neuquen.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-1800, DELTA);
    }

    @Test
    public void test40BuyNeuquenAndBuyAHouseAndHisMoneyDecrementBy4800(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood neuquen = board.getNeighborhoodByName("Neuquén");
        neuquen.buy(player1);
        double start_money = player1.getMoney().getValue();
        neuquen.buyHouse();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-4800, DELTA);
    }

    @Test
    public void test41BuyNeuquenAndBuyAHouseAndOpponentLandsThereAndHisMoneyDecrementBy3800(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        Neighborhood neuquen = board.getNeighborhoodByName("Neuquén");
        neuquen.buy(player1);
        neuquen.buyHouse();

        Turn turn = new Turn(player2);
        double start_money = player2.getMoney().getValue();
        neuquen.playerLandsOnCell(player2, turn);

        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-3800, DELTA);
    }

    @Test
    public void test42BuyTucumanAndOpponentLandsHereAndHisMoneyDecrementBy2500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        Neighborhood tucuman = board.getNeighborhoodByName("Tucuman");
        tucuman.buy(player1);
        Turn turn = new Turn(player2);
        double start_money = player2.getMoney().getValue();
        tucuman.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-2500, DELTA);
    }

    @Test
    public void test43BuyTucumanAndBuyAHouseAndHisMoneyDecrementBy7000(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Neighborhood tucuman = board.getNeighborhoodByName("Tucuman");
        tucuman.buy(player1);
        double start_money = player1.getMoney().getValue();
        tucuman.buyHouse();
        double finish_money = player1.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-7000, DELTA);
    }

    @Test
    public void test44BuyTucumanAndBuyAHouseAndOpponentLandsThereAndHisMoneyDecrementBy4500(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        Neighborhood tucuman = board.getNeighborhoodByName("Tucuman");
        tucuman.buy(player1);
        tucuman.buyHouse();

        Turn turn = new Turn(player2);
        double start_money = player2.getMoney().getValue();
        tucuman.playerLandsOnCell(player2, turn);

        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-4500, DELTA);
    }

    @Test
    public void test45BuyBsAsSurAndBsAsNorteAndBuyBuildingsAndOpponentLandsThereAndHisMoneyDecreasesAccordingToTheRentOfTheBuildings(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        Neighborhood bsasnorte = board.getNeighborhoodByName("Bs. As. - Zona Norte");
        bsassur.buy(player1);
        bsasnorte.buy(player1);

        bsassur.buyHouse();
        double rentprice = bsassur.getRentalPrice().getValue();
        Turn turn = new Turn(player2);
        double start_money = player2.getMoney().getValue();
        bsassur.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        bsassur.buyHouse();
        rentprice = bsassur.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        bsassur.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        bsasnorte.buyHouse();
        rentprice = bsasnorte.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        bsasnorte.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        bsasnorte.buyHouse();
        rentprice = bsasnorte.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        bsasnorte.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        bsasnorte.buyHotel();
        rentprice = bsasnorte.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        bsasnorte.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        bsassur.buyHotel();
        rentprice = bsassur.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        bsassur.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

    }

    @Test
    public void test46BuyCordobaSurAndCordobaNorteAndBuyBuildingsAndOpponentLandsThereAndHisMoneyDecreasesAccordingToTheRentOfTheBuildings(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        Neighborhood cordobasur = board.getNeighborhoodByName("Cordoba - Sur");
        Neighborhood cordobanorte = board.getNeighborhoodByName("Cordoba - Norte");
        cordobasur.buy(player1);
        cordobanorte.buy(player1);

        cordobasur.buyHouse();
        double rentprice = cordobasur.getRentalPrice().getValue();
        Turn turn = new Turn(player2);
        double start_money = player2.getMoney().getValue();
        cordobasur.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        cordobasur.buyHouse();
        rentprice = cordobasur.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        cordobasur.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        cordobanorte.buyHouse();
        rentprice = cordobanorte.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        cordobanorte.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        cordobanorte.buyHouse();
        rentprice = cordobanorte.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        cordobanorte.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        cordobanorte.buyHotel();
        rentprice = cordobanorte.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        cordobanorte.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        cordobasur.buyHotel();
        rentprice = cordobasur.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        cordobasur.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

    }

    @Test
    public void test47BuySaltaSurAndSaltaNorteAndBuyBuildingsAndOpponentLandsThereAndHisMoneyDecreasesAccordingToTheRentOfTheBuildings(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        Neighborhood saltasur = board.getNeighborhoodByName("Salta - Sur");
        Neighborhood saltanorte = board.getNeighborhoodByName("Salta - Norte");
        saltasur.buy(player1);
        saltanorte.buy(player1);

        saltasur.buyHouse();
        double rentprice = saltasur.getRentalPrice().getValue();
        Turn turn = new Turn(player2);
        double start_money = player2.getMoney().getValue();
        saltasur.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        saltasur.buyHouse();
        rentprice = saltasur.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        saltasur.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        saltanorte.buyHouse();
        rentprice = saltanorte.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        saltanorte.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        saltanorte.buyHouse();
        rentprice = saltanorte.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        saltanorte.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        saltanorte.buyHotel();
        rentprice = saltanorte.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        saltanorte.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

        saltasur.buyHotel();
        rentprice = saltasur.getRentalPrice().getValue();
        start_money = player2.getMoney().getValue();
        saltasur.playerLandsOnCell(player2, turn);
        finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

    }

    @Test
    public void test48BuySantaFeAndBuyBuildingsAndOpponentLandsThereAndHisMoneyDecreasesAccordingToTheRentOfTheBuildings(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        Neighborhood santafe = board.getNeighborhoodByName("Santa Fe");
        santafe.buy(player1);

        santafe.buyHouse();
        double rentprice = santafe.getRentalPrice().getValue();
        Turn turn = new Turn(player2);
        double start_money = player2.getMoney().getValue();
        santafe.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

    }

    @Test
    public void test49BuyNeuquenAndBuyBuildingsAndOpponentLandsThereAndHisMoneyDecreasesAccordingToTheRentOfTheBuildings(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        Neighborhood neuquen = board.getNeighborhoodByName("Neuquén");
        neuquen.buy(player1);

        neuquen.buyHouse();
        double rentprice = neuquen.getRentalPrice().getValue();
        Turn turn = new Turn(player2);
        double start_money = player2.getMoney().getValue();
        neuquen.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

    }

    @Test
    public void test50BuyTucumanAndBuyBuildingsAndOpponentLandsThereAndHisMoneyDecreasesAccordingToTheRentOfTheBuildings(){
        Board board = new Board();
        Player player1 = new Player("Diego", board.getStartCell());
        Player player2 = new Player("Lucas", board.getStartCell());
        Neighborhood tucuman = board.getNeighborhoodByName("Tucuman");
        tucuman.buy(player1);

        tucuman.buyHouse();
        double rentprice = tucuman.getRentalPrice().getValue();
        Turn turn = new Turn(player2);
        double start_money = player2.getMoney().getValue();
        tucuman.playerLandsOnCell(player2, turn);
        double finish_money = player2.getMoney().getValue();
        Assert.assertEquals(finish_money, start_money-rentprice, DELTA);

    }

}

