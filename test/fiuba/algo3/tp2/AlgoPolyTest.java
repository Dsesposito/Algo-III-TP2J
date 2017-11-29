package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Cells.StartPoint;
import fiuba.algo3.tp2.model.Exceptions.AlgoPolyPlayerQuantityException;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AlgoPolyTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test01AddThreePlayersToGame(){
        AlgoPoly game = AlgoPoly.getInstance();

        game.addPlayerToGame("Lucas");
        game.addPlayerToGame("Diego");
        game.addPlayerToGame("Guido");

        Assert.assertTrue(game.getQuantityOfPlayers() == 3);
    }

    @Test
    public void test02AddFourPlayersToGame(){
        AlgoPoly game = AlgoPoly.getInstance();

        game.addPlayerToGame("Lucas");
        game.addPlayerToGame("Diego");
        game.addPlayerToGame("Guido");
        thrown.expect(AlgoPolyPlayerQuantityException.class);
        game.addPlayerToGame("Gonazalo");
    }

}

