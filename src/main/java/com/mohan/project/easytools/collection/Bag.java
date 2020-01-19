package com.mohan.project.easytools.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 背包
 *
 * @author mohan
 *************************************************************************
 *  Compilation:  javac Bag.java
 *
 *  A generic bag or multiset, implemented using a linked list.
 *
 *************************************************************************
 *
 * The <tt>Bag</tt> class represents a bag (or multiset) of
 * generic items. It supports insertion and iterating over the
 * items in arbitrary order.
 * <p>
 * The <em>add</em>, <em>isEmpty</em>, and <em>size</em>  operation
 * take constant time. Iteration takes time proportional to the number of items.
 * <p>
 * For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Bag<E> implements Iterable<E> {

    /**
     * number of elements in bag
     */
    private int size;

    /**
     * beginning of bag
     */
    private Node first;

    /**
     * helper linked list class
     */
    private class Node {

        private E element;
        private Node next;
    }

    /**
     * Create an empty stack.
     */
    public Bag() {
        first = null;
        size = 0;
    }

    /**
     * Is the BAG empty?
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items in the bag.
     */
    public int size() {
        return size;
    }

    /**
     * Add the element to the bag.
     */
    public void add(E element) {
        Node old = first;
        first = new Node();
        first.element = element;
        first.next = old;
        size++;
    }

    /**
     * Return an iterator that iterates over the items in the bag.
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
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.element;
            current = current.next;
            return element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
