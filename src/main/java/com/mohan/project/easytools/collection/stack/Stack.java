package com.mohan.project.easytools.collection.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 队列
 *
 * @author mohan
 *************************************************************************
 *  Compilation:  javac Stack.java
 *
 *  A generic stack, implemented using a linked list. Each stack
 *  element is of type E.
 *
 *  % more tobe.txt
 *  to be or not to - be - - that - - - is
 *
 *  % java Stack < tobe.txt
 *  to be not that or be (2 left on stack)
 *
 *************************************************************************
 *
 * The <tt>Stack</tt> class represents a last-in-first-out (LIFO) stack of generic items.
 * It supports the usual <em>push</em> and <em>pop</em> operations, along with methods
 * for peeking at the top element, testing if the stack is empty, and iterating through
 * the items in LIFO order.
 * <p>
 * All stack operations except iteration are constant time.
 * <p>
 * For additional documentation, see <a href="/algs4/13stacks">Section 1.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Stack<E> implements Iterable<E> {

    /**
     * size of the stack
     */
    private int size;

    /**
     * top of stack
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
    public Stack() {
        first = null;
        size = 0;
    }

    /**
     * Is the stack empty?
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items in the stack.
     */
    public int size() {
        return size;
    }

    /**
     * Add the element to the stack.
     */
    public void push(E element) {
        Node oldfirst = first;
        first = new Node();
        first.element = element;
        first.next = oldfirst;
        size++;
    }

    /**
     * Delete and return the element most recently added to the stack.
     * Throw an exception if no such element exists because the stack is empty.
     */
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        //save element to return
        E element = first.element;
        //delete first node
        first = first.next;
        size--;
        //return the saved element
        return element;
    }


    /**
     * Return the element most recently added to the stack.
     * Throw an exception if no such element exists because the stack is empty.
     */
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return first.element;
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
     * Return an iterator to the stack that iterates through the items in LIFO order.
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