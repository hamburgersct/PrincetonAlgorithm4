
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 8;
    private Item[] raque;
    private int size;

    private void resize(int newCap) {
        Item[] newQueue = (Item[]) new Object[newCap];
        for (int i = 0; i < this.size; i++) {
            newQueue[i] = raque[i];
        }
        raque = newQueue;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        raque = (Item[]) new Object[INIT_CAPACITY];
        this.size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot enqueue a null element.");
        }
        if (size() == raque.length) {
            resize(size * 2);
        }
        raque[size++] = item;
    }

    // remove and return a random item
    // exchange the chosen item with the last item, and remove the last one
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot dequeue from an empty queue.");
        }
        int randomIndex = StdRandom.uniform(this.size);
        Item temp = raque[randomIndex];
        if (randomIndex != size - 1) raque[randomIndex] = raque[size - 1];
        raque[--size] = null;

        if (this.size > 0 && this.size < raque.length / 4) {
            resize(raque.length / 2);
        }
        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot sample from an empty queue.");
        }
        return raque[StdRandom.uniform(this.size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {

        private int currIndex;
        private Item[] randomArray;

        public RandomQueueIterator() {
            randomArray = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                randomArray[i] = raque[i];
            }
            StdRandom.shuffle(randomArray);
            currIndex = 0;
        }

        public boolean hasNext() {
            return currIndex < randomArray.length;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No more item.");
            Item returnValue = randomArray[currIndex++];
            return returnValue;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        System.out.println(rq.isEmpty());
        System.out.println(rq.size());
        rq.enqueue("haha");
        rq.enqueue("heihei");
        rq.enqueue("hiahia");
        rq.enqueue("hehe");
        rq.enqueue("houhou");
        System.out.println(rq.size());
        rq.dequeue();
        rq.dequeue();

        Iterator<String> iter = rq.iterator();
        while(iter.hasNext()) System.out.println(iter.next());
        System.out.println();
        Iterator<String> iter2 = rq.iterator();
        while(iter2.hasNext()) System.out.println(iter2.next());
    }

}
