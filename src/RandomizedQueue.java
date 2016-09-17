import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private ArrayList<Item> items;
    private int N = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = new ArrayList<Item>(1);
    }

    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == items.size()) {
            resize(items.size() * 2);
        }
        items.add(item);
        N++;
    }

    private void resize(int capacity) {
        items.ensureCapacity(capacity);
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniform(N);
        Item item = items.get(idx);
        items.set(idx, items.get(0)); // fill caused gap
        N--;
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniform(N);
        return items.get(idx);
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing
    public static void main(String[] args) {
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final int[] order;
        private int n = -1;

        public RandomizedQueueIterator() {
            order = new int[N];
            for (int i = 0; i < N; i++) {
                order[i] = i;
                n++;
            }
            StdRandom.shuffle(order);
        }

        @Override
        public boolean hasNext() {
            return n >= 0;
        }

        @Override
        public Item next() {
            if (n == -1) {
                throw new NoSuchElementException();
            }
            Item item = items.get(order[n]);
            n--;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
