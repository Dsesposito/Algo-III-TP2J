package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cells.Service;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;
import org.junit.Assert;
import org.junit.Test;

public class ServiceTest {
    @Test
    public void test01PlayerLandsOnEdesurOwnedByAnotherPlayer(){

        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());
        Player player2 = new Player("Lucas",board.getStartCell());

        Service  edesur = board.getServiceByName("Edesur");
        edesur.buy(player1);

        Long face1 = 4L;
        Long face2 = 3L;

        Turn turn = new Turn(player2);
        turn.mockDice(face1,face2);

        Double finalMoneyValue = player2.getMoney().getValue() - (turn.getDiceResult() * edesur.getDiceMultiplier());
        edesur.playerLandsOnCell(player2,turn);
        Assert.assertTrue(player2.getMoney().getValue().equals(finalMoneyValue));
    }

    @Test
    public void test02PlayerLandsOnEdesurOwnedByAnotherPlayerWhoAlsoHasAysa(){

        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());
        Player player2 = new Player("Lucas",board.getStartCell());

        Service  edesur = board.getServiceByName("Edesur");
        Service  aysa = board.getServiceByName("Aysa");
        edesur.buy(player1);
        aysa.buy(player1);

        Long face1 = 4L;
        Long face2 = 3L;

        Turn turn = new Turn(player2);
        turn.mockDice(face1,face2);

        Double finalMoneyValue = player2.getMoney().getValue() - (turn.getDiceResult() * edesur.getDiceMultiplier());

        edesur.playerLandsOnCell(player2,turn);

        Assert.assertTrue(player2.getMoney().getValue().equals(finalMoneyValue));
    }

}
