import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by huangqiming on 2017/1/17.
 */
public class TestArrayDeque1B {

    @Test
    public void testAddFirstAndPrintDeque(){
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
        sad1.addFirst(1);
        sad1.addFirst(2);
        sad1.printDeque();
        System.out.println();
        sad1.addFirst(3);
        sad1.printDeque();
    }

    @Test
    public void testAddLast(){
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        sad1.addLast(5);
        sad1.addLast(10);
        sad1.printDeque();
    }

    @Test
    public void testIsEmpty(){
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        assertTrue(sad1.isEmpty());

        FailureSequence fs = new FailureSequence();
        sad1.addFirst(4);
        DequeOperation dequeOp1 = new DequeOperation("addFirst", 4);
        fs.addOperation(dequeOp1);
        assertFalse(fs.toString(), sad1.isEmpty());
    }

    @Test
    public void testSize(){
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        assertEquals(0, sad1.size());
        sad1.addLast(3);
        sad1.addFirst(45);
        assertEquals(2, sad1.size());
    }

    @Test
    public void testRemoveFirst(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        /*test the empty case*/
        assertEquals(null, sad.removeFirst());

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        sad1.addFirst(1);
        sad1.addLast(2);
        sad1.addLast(3);
        int removedItem = sad1.removeFirst();
        assertEquals(1, removedItem);
        StudentArrayDeque<Integer> sad2 = new StudentArrayDeque<>();
        sad2.addLast(2);
        sad2.addLast(3);
        sad1.printDeque();
    }

    @Test
    public void testRemoveLast(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        /*test empty case*/
        assertEquals(null, sad.removeLast());

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        sad1.addLast(1);
        sad1.addLast(2);
        sad1.addLast(3);
        int removedItem = sad1.removeLast();
        assertEquals(3, removedItem);
        StudentArrayDeque<Integer> sad2 = new StudentArrayDeque<>();
        sad2.addLast(1);
        sad2.addLast(2);
        sad1.printDeque();
    }

    @Test
    public void testGet(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        /*empty case*/
        assertEquals(null, sad.get(49));

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        FailureSequence fs = new FailureSequence();
        sad1.addLast(100);
        DequeOperation dequeOp1 = new DequeOperation("addLast", 100);
        fs.addOperation(dequeOp1);
        sad1.addLast(200);
        DequeOperation dequeOp2 = new DequeOperation("addLast", 200);
        fs.addOperation(dequeOp2);
        sad1.addLast(300);
        DequeOperation dequeOp3 = new DequeOperation("addLast", 300);
        fs.addOperation(dequeOp3);
        Integer actuall = sad1.get(9);
        DequeOperation dequeOp4 = new DequeOperation("get", 9);
        fs.addOperation(dequeOp4);
        /*the the index does not exist*/
        assertEquals(fs.toString(), null, actuall);
        sad1.printDeque();
        System.out.println();

        int getedItem = sad1.get(0);
        assertEquals(100, getedItem);
        sad1.printDeque();
    }
}
