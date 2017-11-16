package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cell;
import fiuba.algo3.tp2.model.Neighborhood;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DynamicFowardTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test01LandsOnDynamicForwardAndMoveTwoMoreCellsLessThanTheSumOfTheDice() {

        Player player1 = new Player("Diego");

        Board board = new Board();

        Cell dynamicForwardCell = new Cell("Avance Dinamico",board);

        Long face1 = 2L;
        Long face2 = 4L;

        player1.goToCell(dynamicForwardCell);

        player1.move(face1+face2);

        Long positionsToAdvance = (face1+face2) - 2;

        Cell futureCell = dynamicForwardCell.moveForwardXCells(positionsToAdvance);

        Assert.assertTrue(player1.isInCell(futureCell));

    }

    @Test
    public void test02LandsOnDynamicForwardAndMovesXCellsDependingOnPlayerAmountOfMoney(){
        Player player1 = new Player("Diego");

        Board board = new Board();

        Cell dynamicForwardCell = new Cell("Avance Dinamico",board);

        Long face1 = 5L;
        Long face2 = 4L;

        player1.goToCell(dynamicForwardCell);

        player1.move(face1+face2);

        Long positionsToAdvance = (long) Math.floor(player1.getMoney().modulus(face1+face2));

        Cell futureCell = dynamicForwardCell.moveForwardXCells(positionsToAdvance);

        Assert.assertTrue(player1.isInCell(futureCell));
    }

    @Test
    public void test03LandsOnDynamicForwardAndMovesXCellsDependingOnPlayerAmountOfProperties(){
        Player player1 = new Player("Diego");

        Board board = new Board();

        Cell dynamicForwardCell = new Cell("Avance Dinamico",board);
        Neighborhood bsassur = new Neighborhood("Buenos Aires - Sur");
        bsassur.buy(player1);
        bsassur.buyHouse();
        bsassur.buyHouse();
        Neighborhood neuquen = new Neighborhood("Neuquen");
        neuquen.buy(player1);
        neuquen.buyHotel();

        Long numberOfPlayer1Properties = 5L;

        Long face1 = 6L;
        Long face2 = 6L;

        player1.goToCell(dynamicForwardCell);

        player1.move(face1+face2);

        Long positionsToAdvance = numberOfPlayer1Properties;

        Cell futureCell = dynamicForwardCell.moveForwardXCells(positionsToAdvance);

        Assert.assertTrue(player1.isInCell(futureCell));
    }

}
