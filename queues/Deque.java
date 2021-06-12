/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node first, last;

    private class Node {
        Item item;
        Node prev, next;

        public Node(Item item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }
    }

    // construct an empty deque
    public Deque() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You cannot add null.");
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
            last = first;
        }
        else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        this.size += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You cannot add null.");
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            last = newNode;
            first = last;
        }
        else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        this.size += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("You cannot remove an element from an empty deque.");
        }
        Node temp = first;
        Item retItem = temp.item;
        if (size() > 1) {
            first.next.prev = null;
            first = first.next;
        }
        else {
            first = null;
            last = null;
        }
        temp.next = null;
        this.size -= 1;
        return retItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("You cannot remove an element from an empty deque.");
        }
        Node temp = last;
        Item retItem = temp.item;
        if (size() > 1) {
            last.prev.next = null;
            last = last.prev;
        }
        else {
            last = null;
            first = null;
        }
        temp.prev = null;
        this.size -= 1;
        return retItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node po;

        public DequeIterator() {
            po = first;
        }

        public boolean hasNext() {
            return po != null;
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No more elements.");
            }
            else {
                Item retItem = po.item;
                po = po.next;
                return retItem;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupported Method.");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deq = new Deque<>();
        if (deq.isEmpty()) {
            StdOut.println("Now the deque is empty.");
        }
        deq.addFirst(2);
        deq.addLast(3);
        deq.addFirst(4);
        StdOut.println("size: " + deq.size());
        for (int i : deq) {
            StdOut.print(i + " ");
        }
        int item = deq.removeLast();
        StdOut.println("\nsize: " + deq.size());
        for (int i : deq) {
            StdOut.print(i + " ");
        }
        item = deq.removeFirst();
        StdOut.println("\nAfter removing first element " + item + ", size: " + deq.size());
        for (int i : deq) {
            StdOut.print(i + " ");
        }

    }

}
