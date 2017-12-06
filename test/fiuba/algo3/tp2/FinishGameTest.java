package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Railway;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class FinishGameTest {
    @Test
    public void test01FinishGameDuePlayersHaveADebtAndCanNoPayIt() {

        AlgoPoly game = AlgoPoly.getInstance();
        game.resetGame();
        game.addPlayerToGame("Lucas");
        game.addPlayerToGame("Diego");
        game.addPlayerToGame("Guido");
        game.startGame();

        game.nextTurn();
        Railway subte = game.getBoard().getRailwayByName("Subte");
        subte.buy(game.getActualPlayer());
        Player player1Winner = game.getActualPlayer();

        game.nextTurn();
        game.getActualPlayer().decrementMoney(game.getActualPlayer().getMoney());
        Long face1 = 3L;
        Long face2 = 5L;
        game.mockThrowDice(face1, face2);
        game.movePlayer();
        Player player2Defeated = game.getActualPlayer();

        game.nextTurn();
        game.getActualPlayer().decrementMoney(game.getActualPlayer().getMoney());
        face1 = 3L;
        face2 = 5L;
        game.mockThrowDice(face1, face2);
        game.movePlayer();
        Player player3Defeated = game.getActualPlayer();

        Assert.assertTrue(!player1Winner.isDefeated() && player2Defeated.isDefeated() && player3Defeated.isDefeated());
    }
}
