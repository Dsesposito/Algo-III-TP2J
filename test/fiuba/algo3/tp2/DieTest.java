package fiuba.algo3.tp2;
import fiuba.algo3.tp2.model.Die;
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

}
