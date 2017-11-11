package fiuba.algo3.tp2;

import fiuba.algo3.tp2.model.Exceptions.PlayerMovementInJailException;
import fiuba.algo3.tp2.model.Player;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JailTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test01PlayerInJailCanNotMoveInFirstTurn(){
        Player player1 = new Player("Diego");
        player1.goToJail();

        thrown.expect(PlayerMovementInJailException.class);

        player1.moveFoward();

    }

}
