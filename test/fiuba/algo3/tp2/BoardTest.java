package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Exceptions.BoardPlayerQuantityException;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BoardTest {

    private static Long boardTotalCells = Global.config.getLong("boardTotalCells");

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test01CreateBoard(){
        Board board = new Board();

        Assert.assertTrue(board.getCells().size() == boardTotalCells);

    }

    @Test
    public void test02AddThreePlayersToBoard(){
        Board board = new Board();

        Player player1 = new Player("Lucas");
        Player player2 = new Player("Diego");
        Player player3 = new Player("Guido");

        board.addPlayerToBoard(player1);
        board.addPlayerToBoard(player2);
        board.addPlayerToBoard(player3);

        Assert.assertTrue(board.getQuantityOfPlayers() == 3);
    }

    @Test
    public void test03AddFourPlayersToBoard(){
        Board board = new Board();

        Player player1 = new Player("Lucas");
        Player player2 = new Player("Diego");
        Player player3 = new Player("Guido");
        Player player4 = new Player("Gonzalo");

        board.addPlayerToBoard(player1);
        board.addPlayerToBoard(player2);
        board.addPlayerToBoard(player3);

        thrown.expect(BoardPlayerQuantityException.class);
        board.addPlayerToBoard(player4);
    }
}
