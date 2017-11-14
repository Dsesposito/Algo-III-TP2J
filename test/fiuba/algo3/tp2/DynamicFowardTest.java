package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Cell;
import fiuba.algo3.tp2.model.Player;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DynamicFowardTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test01LandsOnDynamicFowardAndMoveTwoMoreCellsLessThanTheSumOfTheDice() {

        Player player1 = new Player("Diego");

        Cell dynamicFowardCell = new Cell("DynamicFoward");

        Long face1 = 2L;
        Long face2 = 4L;

        player1.goToCell(dynamicFowardCell);

        player1.move(face1+face2);

        Long positionsToAdvance = (face1+face2) - 2;

        Cell futureCell = dynamicFowardCell.moveFowardXCells(positionsToAdvance);

        Assert.assertTrue(player1.isInCell(futureCell));

    }

}
