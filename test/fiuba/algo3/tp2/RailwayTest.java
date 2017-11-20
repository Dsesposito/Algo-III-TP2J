package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cells.Railway;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;
import org.junit.Assert;
import org.junit.Test;

public class RailwayTest {

    @Test
    public void test01PlayerLandsOnTrainsOwnedByAnotherPlayer(){

        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());
        Player player2 = new Player("Lucas",board.getStartCell());

        Railway train = board.getRailwayByName("Tren");
        train.buy(player1);

        Long face1 = 4L;
        Long face2 = 3L;

        Turn turn = new Turn(player2);
        turn.mockDice(face1,face2);

        Double finalMoneyValue = player2.getMoney().getValue() - (turn.getDiceResult() * train.getDiceMultiplier());

        train.playerLandsOnCell(player2,turn);

        Assert.assertTrue(player2.getMoney().getValue().equals(finalMoneyValue));
    }

    @Test
    public void test02PlayerLandsOnTrainOwnedByAnotherPlayerWhoAlsoHasSubway(){

        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());
        Player player2 = new Player("Lucas",board.getStartCell());

        Railway train = board.getRailwayByName("Tren");
        Railway subway = board.getRailwayByName("Subte");
        train.buy(player1);
        subway.buy(player1);

        Long face1 = 4L;
        Long face2 = 3L;

        Turn turn = new Turn(player2);
        turn.mockDice(face1,face2);

        Double finalMoneyValue = player2.getMoney().getValue() - (turn.getDiceResult() * train.getDiceMultiplier());

        train.playerLandsOnCell(player2,turn);

        Assert.assertTrue(player2.getMoney().getValue().equals(finalMoneyValue));
    }



}
