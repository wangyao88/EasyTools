package com.mohan.project.easytools.collection.stack;

/**
 *
 * 动态调整容量栈
 * @author mohan
 *************************************************************************
 *  Compilation:  javac ResizingArrayStack.java
 *
 *  Stack implementation with elements resizing array.
 *
 *  % more tobe.txt 
 *  to be or not to - be - - that - - - is
 *
 *  % java ResizingArrayStack < tobe.txt
 *  to be not that or be (2 left on stack)
 *
 *************************************************************************
 *
 * suppress unchecked warnings in Java 1.5.0_6 and later
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ResizingArrayStack<E> implements Iterable<E> {

    /**
     * array of elements
     */
    private E[] elements;

    /**
     * number of elements on stack
     */
    private int size = 0;

    /**
     * create an empty stack
     */
    public ResizingArrayStack() {
        elements = (E[]) new Object[2];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }


    /**
     * resize the underlying array holding the elements
     *
     * @param capacity
     */
    private void resize(int capacity) {
        assert capacity >= size;
        E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            temp[i] = elements[i];
        elements = temp;
    }

    /**
     * push elements new item onto the stack
     *
     * @param item
     */
    public void push(E item) {
        if (size == elements.length) {
           //double size of array if necessary
            resize(2 * elements.length);
        }
        //add item
        elements[size++] = item;
    }

    /**
     * delete and return the item most recently added
     *
     * @return
     */
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow error");
        }
        E item = elements[size - 1];
        //to avoid loitering
        elements[size - 1] = null;
        size--;
        // shrink size of array if necessary
        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }
        return item;
    }


    @Override
    public Iterator<E> iterator() {
        return new LIFOIterator();
    }

    /**
     * an iterator, doesn't implement remove() since it's optional
     */
    private class LIFOIterator implements Iterator<E> {

        private int i = size;

        @Override
        public boolean hasNext() {
            return i > 0;
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
            return elements[--i];
        }
    }
}
