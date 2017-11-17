package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Cells.DynamicForward;
import fiuba.algo3.tp2.model.Cells.Neighborhood;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DynamicForwardTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test01LandsOnDynamicForwardAndMoveTwoMoreCellsLessThanTheSumOfTheDice() {

        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());


        DynamicForward dynamicForwardCell = board.getDynamicForward();

        Long face1 = 2L;
        Long face2 = 4L;

        player1.goToCell(dynamicForwardCell);

        player1.move(face1+face2);

        Long positionsToAdvance = (face1+face2) - 2;

        Cell futureCell = dynamicForwardCell.moveForwardXCells(positionsToAdvance);

        Assert.assertTrue(player1.isInCell(futureCell));

    }

    @Test
    public void test02LandsOnDynamicForwardAndMovesXCellsDependingOnPlayerAmountOfMoney() {

        Board board = new Board();
        Player player1 = new Player("Diego",board.getStartCell());

        DynamicForward dynamicForwardCell = board.getDynamicForward();

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

        Board board = new Board();

        Player player1 = new Player("Diego",board.getStartCell());

        DynamicForward dynamicForwardCell = board.getDynamicForward();
        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        bsassur.buy(player1);
        bsassur.buyHouse();
        bsassur.buyHouse();
        Neighborhood neuquen = board.getNeighborhoodByName("Neuquén");
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
