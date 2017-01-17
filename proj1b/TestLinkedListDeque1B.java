import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by huangqiming on 2017/1/17.
 */
public class TestLinkedListDeque1B {
    @Test
    public void testAddFirstAndPrintDeque(){
        StudentLinkedListDeque<Integer> sad1 = new StudentLinkedListDeque<>();
        sad1.addFirst(1);
        sad1.addFirst(2);
        sad1.printDeque();
        System.out.println();
        sad1.addFirst(3);
        sad1.printDeque();
    }

    @Test
    public void testAddLast(){
        StudentLinkedListDeque<Integer> sad1 = new StudentLinkedListDeque<>();
        sad1.addLast(5);
        sad1.addLast(10);
        sad1.printDeque();
    }

    @Test
    public void testIsEmpty(){
        StudentLinkedListDeque<Integer> sad1 = new StudentLinkedListDeque<>();
        assertTrue(sad1.isEmpty());
        sad1.addFirst(4);
        assertFalse(sad1.isEmpty());
    }

    @Test
    public void testSize(){
        StudentLinkedListDeque<Integer> sad1 = new StudentLinkedListDeque<>();
        assertEquals(0, sad1.size());
        sad1.addLast(3);
        sad1.addFirst(45);
        assertEquals(2, sad1.size());
    }

    @Test
    public void testRemoveFirst(){
        StudentLinkedListDeque<Integer> sad = new StudentLinkedListDeque<>();
        /*test the empty case*/
        assertEquals(null, sad.removeFirst());

        StudentLinkedListDeque<Integer> sad1 = new StudentLinkedListDeque<>();
        sad1.addFirst(5);
        sad1.addLast(3);
        int removedItem = sad1.removeFirst();
        assertEquals(5, removedItem);
        sad1.printDeque();
    }

    @Test
    public void testRemoveLast(){
        StudentLinkedListDeque<Integer> sad = new StudentLinkedListDeque<>();
        /*test empty case*/
        assertEquals(null, sad.removeLast());

        StudentLinkedListDeque<Integer> sad1 = new StudentLinkedListDeque<>();
        sad1.addLast(1);
        sad1.addLast(2);
        sad1.addLast(3);
        int removedItem = sad1.removeLast();
        assertEquals(3, removedItem);
        sad1.printDeque();
    }

    @Test
    public void testGet(){
        StudentLinkedListDeque<Integer> sad = new StudentLinkedListDeque<>();
        /*empty case*/
        assertEquals(null, sad.get(49));

        StudentLinkedListDeque<Integer> sad1 = new StudentLinkedListDeque<>();
        FailureSequence fs = new FailureSequence();
        sad1.addLast(100);
        DequeOperation op1 = new DequeOperation("addLast", 100);
        fs.addOperation(op1);
        sad1.addLast(200);
        DequeOperation op2 = new DequeOperation("addLast", 200);
        fs.addOperation(op2);
        sad1.addLast(300);
        DequeOperation op3 = new DequeOperation("addLast", 300);
        fs.addOperation(op3);
        Integer getItem = sad1.get(1000);
        DequeOperation op4 = new DequeOperation("get", 1000);
        fs.addOperation(op4);
        /*the the index does not exist*/
        assertEquals(fs.toString(), null, getItem);
        sad1.printDeque();
        System.out.println();

        int getedItem = sad1.get(0);
        assertEquals(100, getedItem);
        sad1.printDeque();
        System.out.println();
    }
}
