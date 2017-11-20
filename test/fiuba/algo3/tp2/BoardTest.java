package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Board;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BoardTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test01CreateBoard(){
        Board board = new Board();

        Assert.assertTrue(board.getCells().size() == 20);

    }
}
