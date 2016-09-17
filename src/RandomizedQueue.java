import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private ArrayList<Item> items;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = new ArrayList<Item>(1);
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == items.size()) {
            resize(items.size() * 2);
        }
        items.add(item);
        size++;
    }

    private void resize(int capacity) {
        items.ensureCapacity(capacity);
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniform(size);
        Item item = items.get(idx);
        items.set(idx, items.get(0)); // fill caused gap
        size--;
        if (size > 0 && size < items.size() / 4) {
            resize(items.size() / 2);
        }
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniform(size);
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
            order = new int[size];
            for (int i = 0; i < size; i++) {
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
