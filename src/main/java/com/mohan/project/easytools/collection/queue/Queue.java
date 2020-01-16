package com.mohan.project.easytools.collection.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 队列
 *
 * @author mohan
 *************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt
 *
 *  A generic queue, implemented using a linked list.
 *
 *  % java Queue < tobe.txt
 *  to be or not to be (2 left on queue)
 *
 *************************************************************************
 *
 * The <tt>Queue</tt> class represents a first-in-first-out (FIFO)
 * queue of generic items.
 * It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the top element,
 * testing if the queue is empty, and iterating through
 * the items in FIFO order.
 * <p>
 * All queue operations except iteration are constant time.
 * <p>
 * For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Queue<E> implements Iterable<E> {

    /**
     * number of elements on queue
     */
    private int size;

    /**
     * beginning of queue
     */
    private Node first;

    /**
     * end of queue
     */
    private Node last;

    /**
     * helper linked list class
     */
    private class Node {

        private E element;
        private Node next;
    }

    /**
     * Create an empty queue.
     */
    public Queue() {
        first = null;
        last = null;
    }

    /**
     * Is the queue empty?
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items in the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Return the element least recently added to the queue.
     * Throw an exception if the queue is empty.
     */
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        return first.element;
    }

    /**
     * Add the element to the queue.
     */
    public void enqueue(E element) {
        Node x = new Node();
        x.element = element;
        if (isEmpty()) {
            first = x;
            last = x;
        } else {
            last.next = x;
            last = x;
        }
        size++;
    }

    /**
     * Remove and return the element on the queue least recently added.
     * Throw an exception if the queue is empty.
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        E element = first.element;
        first = first.next;
        size--;
        /*
         * to avoid loitering
         */
        if (isEmpty()) {
            last = null;
        }
        return element;
    }

    /**
     * Return string representation.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (E element : this) {
            s.append(element + " ");
        }
        return s.toString();
    }


    /**
     * Return an iterator that iterates over the items on the queue in FIFO order.
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    /**
     * an iterator, doesn't implement remove() since it's optional
     */
    private class ListIterator implements Iterator<E> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.element;
            current = current.next;
            return element;
        }
    }
}
