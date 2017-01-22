package creatures;
import huglife.*;
import org.junit.Test;

import javax.print.attribute.standard.DialogTypeSelection;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;

/**
 * Created by huangqiming on 2017/1/22.
 */
public class TestClorus {

    @Test
    public void testReplicate() {
        Clorus cp = new Clorus(2);
        Clorus cs = cp.replicate();
        assertNotSame(cp, cs);
        assertEquals(1.0, cp.energy(), 0.01);
        assertEquals(1.0, cs.energy(), 0.01);
    }

    @Test
    public void testChoose(){
        Clorus c = new Clorus(5);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.TOP, new Plip());
        surrounded.put(Direction.LEFT, new Plip(1.2));
        surrounded.put(Direction.RIGHT, new Clorus());
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);
    }
}
