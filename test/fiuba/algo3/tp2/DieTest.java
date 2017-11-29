package fiuba.algo3.tp2;
import fiuba.algo3.tp2.model.*;
import org.junit.Assert;
import org.junit.Test;



public class DieTest {

    @Test
    public void test01LandsOnPoliceAndCanNotDoActions() {
        Die die1 = new Die();
        Integer num;
        for(int i=0; i<=100; i=i+1){
            num = die1.throwDie();
            Assert.assertTrue(1 <= num && num <= 6);
        }
    }

    @Test
    public void test02GetEqualNumbersForFirstTimeAndPlayAgain() {

        AlgoPoly game = AlgoPoly.getInstance();
        game.resetGame();
        game.addPlayerToGame("Lucas");
        game.addPlayerToGame("Diego");
        game.addPlayerToGame("Guido");
        game.startGame();

        game.nextTurn();
        Long face1 = 2L;
        Long face2 = 2L;
        game.mockThrowDice(face1,face2);
        Assert.assertTrue(game.getActualTurn().playAgain());
        game.movePlayer();
        Assert.assertTrue(game.getActualTurn().playAgain());
    }

    @Test
    public void test03GetEqualNumbersForSecondConsecutiveTimeAndNotCanPlayAgain() {

        AlgoPoly game = AlgoPoly.getInstance();
        game.resetGame();
        game.addPlayerToGame("Lucas");
        game.addPlayerToGame("Diego");
        game.addPlayerToGame("Guido");
        game.startGame();

        game.nextTurn();
        Long face1 = 2L;
        Long face2 = 2L;
        game.mockThrowDice(face1,face2);
        Assert.assertTrue(game.getActualTurn().playAgain());
        game.movePlayer();
        Assert.assertTrue(game.getActualTurn().playAgain());

        face1 = 3L;
        face2 = 3L;
        game.mockThrowDice(face1,face2);
        Assert.assertTrue(!game.getActualTurn().playAgain());
    }

}