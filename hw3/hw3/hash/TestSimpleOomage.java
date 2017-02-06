package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;

public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        int a = 5;
        int b = 10;
        int c = 20;
        SimpleOomage ooA = new SimpleOomage(a, b, c);
        SimpleOomage ooB = new SimpleOomage(b, c, a);
        SimpleOomage ooC = new SimpleOomage(c, a, b);
        SimpleOomage ooD = new SimpleOomage(a, c, b);
        SimpleOomage ooE = new SimpleOomage(b, a, c);
        SimpleOomage ooF = new SimpleOomage(c, b, a);
        assertNotEquals(ooA.hashCode(), ooB.hashCode());
        assertNotEquals(ooC.hashCode(), ooD.hashCode());
        assertNotEquals(ooE.hashCode(), ooF.hashCode());
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<SimpleOomage>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
