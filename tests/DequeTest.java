import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void CreateInstance() {
        Deque<Integer> deque = new Deque<Integer>();
        assertTrue(deque.isEmpty());
        deque.addFirst(1);
        deque.addFirst(2);
        assertFalse(deque.isEmpty());
        Integer first = deque.removeFirst();
        assertEquals("removed element should be the same we put in", (int) first, 2);
        assertFalse(deque.isEmpty());
        deque.addLast(3);
        assertEquals((int) deque.removeLast(), 3);
        assertEquals((int) deque.removeLast(), 1);
        assertTrue(deque.isEmpty());

        deque.addLast(1);
        deque.addLast(2);
        assertEquals((int) deque.removeFirst(), 1);
        assertEquals((int) deque.removeFirst(), 2);
        assertTrue(deque.isEmpty());
    }

    @Test
    public void throwNPEOnInsertFirstNull() throws Exception {
        thrown.expect(NullPointerException.class);
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(null);
    }

    @Test
    public void throwNPEOnInsertLastNull() throws Exception {
        thrown.expect(NullPointerException.class);
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(null);
    }

    @Test
    public void throwNoSuchElementWhenRemovingFromEmpty() throws Exception {
        thrown.expect(NoSuchElementException.class);
        Deque<Integer> deque = new Deque<Integer>();
        deque.removeLast();
    }

    @Test
    public void throwUnsupportedOperationWhenRemovingFromIterator() throws Exception {
        thrown.expect(UnsupportedOperationException.class);
        Deque<Integer> deque = new Deque<Integer>();
        Iterator<Integer> iter = deque.iterator();
        iter.remove();
    }

    @Test
    public void throwNoSuchElementWhenCallingIteratorNextOnEmpty() throws Exception {
        thrown.expect(NoSuchElementException.class);
        Deque<Integer> deque = new Deque<Integer>();
        Iterator<Integer> iter = deque.iterator();
        iter.next();
    }

    @Test
    public void iterateTest() throws Exception {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        Iterator<Integer> iter = deque.iterator();
        assertTrue(iter.hasNext());
        assertEquals((int) iter.next(), 2);
        assertEquals((int) iter.next(), 1);
        assertFalse(iter.hasNext());
    }

    @Test
    public void size() {
        Deque<Integer> deque = new Deque<Integer>();
        assertEquals(deque.size(), 0);
        deque.addFirst(1);
        assertEquals(deque.size(), 1);
        deque.addLast(2);
        assertEquals(deque.size(), 2);
    }
}