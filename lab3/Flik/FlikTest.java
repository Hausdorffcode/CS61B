import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by huangqiming on 2017/1/16.
 */
public class FlikTest {
    @Test
    public void testisSameNumber(){
        int a = 127;
        int b = 127;
        int c = 128;
        int d = 128;
        assertTrue( Flik.isSameNumber(a, b));
        assertTrue(Flik.isSameNumber(c, d));

    }
}
