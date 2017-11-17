package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Cells.DynamicBackward;
import fiuba.algo3.tp2.model.Cells.Neighborhood;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DynamicBackwardTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test01LandsOnDynamicBackwardAndMoveTwoMoreCellsLessThanTheSumOfTheDice() {

        Board board = new Board();

        Player player1 = new Player("Diego",board.getStartCell());

        DynamicBackward dynamicBackwardCell = board.getDynamicBackward();

        Long face1 = 5L;
        Long face2 = 6L;

        player1.goToCell(dynamicBackwardCell);

        player1.move(face1+face2);

        Long positionsToGoesBack = (face1+face2) - 2;

        Cell futureCell = dynamicBackwardCell.moveBackwardXCells(positionsToGoesBack);

        Assert.assertTrue(player1.isInCell(futureCell));

    }

    @Test
    public void test02LandsOnDynamicBackwardAndMovesXCellsDependingOnPlayerAmountOfMoney(){
        Board board = new Board();

        Player player1 = new Player("Diego",board.getStartCell());

        DynamicBackward dynamicBackwardCell = board.getDynamicBackward();

        Long face1 = 5L;
        Long face2 = 4L;

        player1.goToCell(dynamicBackwardCell);

        player1.move(face1+face2);

        Long positionsToGoesBack = (long) Math.floor(player1.getMoney().modulus(face1+face2));

        Cell futureCell = dynamicBackwardCell.moveBackwardXCells(positionsToGoesBack);

        Assert.assertTrue(player1.isInCell(futureCell));
    }

    @Test
    public void test03LandsOnDynamicBackwardAndMovesXCellsDependingOnPlayerAmountOfProperties(){
        Board board = new Board();

        Player player1 = new Player("Diego",board.getStartCell());

        DynamicBackward dynamicBackwardCell = board.getDynamicBackward();

        Neighborhood bsassur = board.getNeighborhoodByName("Bs. As. - Zona Sur");
        bsassur.buy(player1);
        bsassur.buyHouse();
        bsassur.buyHouse();
        Neighborhood neuquen = board.getNeighborhoodByName("Neuquén");
        neuquen.buy(player1);
        neuquen.buyHotel();

        Long numberOfPlayer1Properties = 5L;

        Long face1 = 1L;
        Long face2 = 2L;

        player1.goToCell(dynamicBackwardCell);

        player1.move(face1+face2);

        Long positionsToGoesBack = numberOfPlayer1Properties;

        Cell futureCell = dynamicBackwardCell.moveBackwardXCells(positionsToGoesBack);

        Assert.assertTrue(player1.isInCell(futureCell));
    }

}
