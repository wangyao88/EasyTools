package com.mohan.project.easytools.collection.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 双向队列
 *
 * @author mohan
 */
public class Deque<E> implements Iterable<E> {

    private int size;
    private Node first;
    private Node last;

    private class Node {

        private E element;
        private Node pre;
        private Node next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void pushLeft(E element) {
        Node node = new Node();
        node.element = element;
        if(isEmpty()) {
            first = node;
            last = node;
        }else {
            node.next = first;
            first.pre = node;
            first = node;
        }
        size++;
    }

    public void pushRight(E element) {
        Node node = new Node();
        node.element = element;
        if(isEmpty()) {
            first = node;
            last = node;
        }else {
            node.pre = last;
            last.next = node;
            last = node;
        }
        size++;
    }

    public E popLeft() {
        if (isEmpty()) {
            throw new RuntimeException("Deque underflow");
        }
        E element = first.element;
        if(size == 1) {
            first = null;
            last = null;
        }else {
            first.next.pre = null;
            first = first.next;
        }
        size--;
        return element;
    }

    public E popRight() {
        if (isEmpty()) {
            throw new RuntimeException("Deque underflow");
        }
        E element = last.element;
        if(size == 1) {
            first = null;
            last = null;
        }else {
            last.pre.next = null;
            last = last.pre;
        }
        size--;
        return element;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private class ListIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return !isEmpty();
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
            return popLeft();
        }
    }
}
