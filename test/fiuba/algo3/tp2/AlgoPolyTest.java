package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.AlgoPoly;
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
        AlgoPoly game = new AlgoPoly();

        Player player1 = new Player("Lucas");
        Player player2 = new Player("Diego");
        Player player3 = new Player("Guido");

        game.addPlayerToGame(player1);
        game.addPlayerToGame(player2);
        game.addPlayerToGame(player3);

        Assert.assertTrue(game.getQuantityOfPlayers() == 3);
    }

    @Test
    public void test02AddFourPlayersToGame(){
        AlgoPoly game = new AlgoPoly();

        Player player1 = new Player("Lucas");
        Player player2 = new Player("Diego");
        Player player3 = new Player("Guido");
        Player player4 = new Player("Gonzalo");

        game.addPlayerToGame(player1);
        game.addPlayerToGame(player2);
        game.addPlayerToGame(player3);

        thrown.expect(AlgoPolyPlayerQuantityException.class);
        game.addPlayerToGame(player4);
    }

}

