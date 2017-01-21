package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(10);
		arb.enqueue(1);
		arb.enqueue(2);
		arb.enqueue(3);
		arb.enqueue(4);
		arb.enqueue(50);
		int actual1 = arb.dequeue();
		assertEquals(1, actual1);
		int actual2 = arb.peek();
		assertEquals(2, actual2);
		assertEquals(10, arb.capacity());
		assertEquals(4, arb.fillCount());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
