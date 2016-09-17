import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class RandomizedQueueTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void CreateInstance() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        assertFalse(queue.isEmpty());
        int first = queue.dequeue();
        int second = queue.dequeue();
        assertTrue(first != second);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void IndependentIteratorsOnSameQueue() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        for (int i = 0; i <= 1000; i++) {
            queue.enqueue(i);
        }

        ArrayList<Integer> items1 = new ArrayList<Integer>();
        for (Integer item : queue) {
            items1.add(item);
        }

        ArrayList<Integer> items2 = new ArrayList<Integer>();
        for (Integer item : queue) {
            items2.add(item);
        }

        Assert.assertThat(items1, IsNot.not(IsEqual.equalTo(items2)));
    }

    @Test
    public void SampleShouldNotReturnNulls() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        for (int i = 0; i <= 10; i++) {
            queue.enqueue(i);
        }

        for (int i = 0; i < 10; i++) {
            queue.dequeue();
            Integer sampled = queue.sample();
            assertTrue(sampled != null);
            queue.enqueue(i);
        }
    }

    @Test
    public void throwNPEOnInsertNull() throws Exception {
        thrown.expect(NullPointerException.class);
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(null);
    }

    @Test
    public void throwNoSuchElementWhenRemovingFromEmpty() throws Exception {
        thrown.expect(NoSuchElementException.class);
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.dequeue();
    }

    @Test
    public void throwNoSuchElementWhenSamplingEmpty() throws Exception {
        thrown.expect(NoSuchElementException.class);
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.sample();
    }

    @Test
    public void samplingShouldNotChangeSize() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(2);
        assertFalse(queue.isEmpty());
        assertEquals((int) queue.sample(), 2);
        assertEquals((int) queue.sample(), 2);
        assertEquals((int) queue.sample(), 2);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void throwUnsupportedOperationWhenRemovingFromIterator() throws Exception {
        thrown.expect(UnsupportedOperationException.class);
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        Iterator<Integer> iter = queue.iterator();
        iter.remove();
    }

    @Test
    public void throwNoSuchElementWhenCallingIteratorNextOnEmpty() throws Exception {
        thrown.expect(NoSuchElementException.class);
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        Iterator<Integer> iter = queue.iterator();
        iter.next();
    }
}