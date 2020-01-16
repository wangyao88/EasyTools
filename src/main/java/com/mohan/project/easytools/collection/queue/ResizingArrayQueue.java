package com.mohan.project.easytools.collection.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 动态调整容量队列
 *
 * @author mohan
 *************************************************************************
 *  Compilation:  javac ResizingArrayQueue.java
 *  Execution:    java ResizingArrayQueue < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt
 *
 *  Queue implementation with a resizing array.
 *
 *  % java ResizingArrayQueue < tobe.txt
 *  to be or not to be (2 left on queue)
 *
 *************************************************************************
 *
 * suppress unchecked warnings in Java 1.5.0_6 and later
 */
@SuppressWarnings("unchecked")
public class ResizingArrayQueue<E> implements Iterable<E> {

    /**
     * queue elements
     */
    private E[] queue;

    /**
     * number of elements on queue
     */
    private int size = 0;

    /**
     * index of first element of queue
     */
    private int first = 0;

    /**
     * index of next available slot
     */
    private int last = 0;

    /**
     * cast needed since no generic array creation in Java
     */
    public ResizingArrayQueue() {
        queue = (E[]) new Object[2];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * resize the underlying array
     *
     * @param max
     */
    private void resize(int max) {
        assert max >= size;
        E[] temp = (E[]) new Object[max];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[(first + i) % queue.length];
        }
        queue = temp;
        first = 0;
        last = size;
    }


    public void enqueue(E item) {
        /*
         double size of array if necessary and recopy to front of array
         */
        if (size == queue.length) {
            /*
            double size of array if necessary
             */
            resize(2 * queue.length);
        }
        /*
        add item
         */
        queue[last++] = item;
        /*
        wrap-around
         */
        if (last == queue.length) {
            last = 0;
        }
        size++;
    }

    /**
     * remove the least recently added item
     *
     * @return
     */
    public E dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue underflow");
        E item = queue[first];
        /*
        to avoid loitering
         */
        queue[first] = null;
        size--;
        first++;
        /*
        wrap-around
         */
        if (first == queue.length) {
            first = 0;
        }
        // shrink size of array if necessary
        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    /**
     * an iterator, doesn't implement remove() since it's optional
     */
    private class QueueIterator implements Iterator<E> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < size;
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
            E item = queue[i % queue.length];
            i++;
            return item;
        }
    }
}