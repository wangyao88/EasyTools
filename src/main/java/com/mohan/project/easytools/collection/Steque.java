package com.mohan.project.easytools.collection;

import java.util.Iterator;

/**
 * 一个以栈为目标的队列(或称为steque)，是一种支持push、pop、和enqueue操作的数据类型
 *
 * @author mohan
 */
public class Steque<E> implements Iterable<E> {

    private int size;
    private Node first;
    private Node last;

    private class Node {

        private E element;
        private Node next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(E element) {
        Node old = first;
        first = new Node();
        first.element = element;
        first.next = old;
        if (isEmpty()) {
            last = first;
        }
        size++;
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E element = first.element;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return element;
    }

    public void enqueue(E element) {
        Node old = last;
        last = new Node();
        last.element = element;
        if (isEmpty()) {
            first = last;
        } else {
            old.next = last;
        }
        size++;
    }

    public E dequeue() {
        return pop();
    }


    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

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
            E element = current.element;
            current = current.next;
            return element;
        }
    }
}
