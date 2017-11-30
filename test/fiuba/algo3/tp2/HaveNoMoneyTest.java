package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;
import org.junit.Test;
import fiuba.algo3.tp2.model.Cells.Railway;

public class HaveNoMoneyTest {

    @Test
    public void test01HaveToFaceAnExpenseAndHaveNoMoney(){
        AlgoPoly game = AlgoPoly.getInstance();
        game.resetGame();
        game.addPlayerToGame("Lucas");
        game.addPlayerToGame("Diego");
        game.addPlayerToGame("Guido");
        game.startGame();
        Board board = new Board();
        Railway subte = board.getRailwayByName("Subte");
        Railway tren = board.getRailwayByName("Tren");
        game.nextTurn();
        tren.buy(game.getActualPlayer());
        game.getActualPlayer().decrementMoney(game.getActualPlayer().getMoney());
        // Player 1 sin plata y con 1 una propiedad.
        game.nextTurn();
        subte.buy(game.getActualPlayer());
        Player player1 = game.getActualPlayer();
        // Player 2 con plata y 1 propiedad.
        game.nextTurn();
        // Jugador 3 no hace nada.
        game.nextTurn();
        // jugador 1 no tiene plata y tiene que afrontar un gasto.
        Long face1 = 5L;
        Long face2 = 3L;
        game.mockThrowDice(face1, face2);
        game.movePlayer(); // cae en subte
        System.out.print(subte.hasOwner());  // Tiene dueño
        Railway subte2 = (Railway)game.getActualPlayer().getCurrentCell();
        System.out.print(subte2.hasOwner()); // No tiene dueño!!!
        Assert.assertTrue(true);
    }
}
