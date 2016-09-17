import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Object[] items;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = new Object[1];
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
        if (size == items.length) {
            resize(items.length * 2);
        }
        size++;
        items[size - 1] = item;
    }

    private void resize(int capacity) {
        if (capacity == 0) {
            capacity = 1;
        }
        Object[] copy = new Object[capacity];
        int numItemsToCopy = items.length;
        if (capacity < numItemsToCopy) {
            numItemsToCopy = capacity;
        }
        System.arraycopy(items, 0, copy, 0, numItemsToCopy);
        items = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniform(size);
        @SuppressWarnings("unchecked")
        Item item = (Item) items[idx];
        items[idx] = items[size - 1]; // fill caused gap
        if (size > 0) {
            items[size - 1] = null;
        }
        size--;
        if (size > 0 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniform(size);
        @SuppressWarnings("unchecked")
        Item item = (Item) items[idx];
        return item;
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
            @SuppressWarnings("unchecked")
            Item item = (Item) items[order[n]];
            n--;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
