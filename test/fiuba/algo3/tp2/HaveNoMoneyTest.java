package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;
import org.junit.Test;
import fiuba.algo3.tp2.model.Cells.Railway;

public class HaveNoMoneyTest {
    private static final double DELTA = 1e-15;

    @Test
    public void test01HaveToFaceAnExpenseAndHaveNoMoney(){
        AlgoPoly game = AlgoPoly.getInstance();
        game.resetGame();
        game.addPlayerToGame("Lucas");
        game.addPlayerToGame("Diego");
        game.addPlayerToGame("Guido");
        game.startGame();
        Railway subte = game.getBoard().getRailwayByName("Subte");
        Railway tren = game.getBoard().getRailwayByName("Tren");
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
        Long face1 = 3L;
        Long face2 = 5L;
        game.mockThrowDice(face1, face2);
        game.movePlayer();

        game.nextTurn();
        // jugador 1 no tiene plata y tiene que afrontar un gasto.
        face1 = 5L;
        face2 = 3L;
        game.mockThrowDice(face1, face2);
        game.movePlayer(); // cae en subte
        // No tiene plata y tiene que vender. Luego salda la deuda.
        game.getActualPlayer().getOwneableCells().get(0).sell();
        game.getActualPlayer().solveDebt();

        game.nextTurn();
        //player2 compra el Tren.
        tren.buy(game.getActualPlayer());
        Player player2 = game.getActualPlayer();
        Double moneyPlayer2 = game.getActualPlayer().getMoney().getValue();
        game.nextTurn();
        // El tercer jugador cae en tren.
        face1 = 3L;
        face2 = 5L;
        game.mockThrowDice(face1, face2);
        game.movePlayer();
        Assert.assertTrue(moneyPlayer2 < player2.getMoney().getValue());
    }
}
