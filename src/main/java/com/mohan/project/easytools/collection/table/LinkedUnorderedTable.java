package com.mohan.project.easytools.collection.table;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 无序链表实现的table
 *
 * @param <Key>   键
 * @param <Value> 值
 */
public class LinkedUnorderedTable<Key extends Comparable<Key>, Value> implements Table<Key, Value> {

    private Node first;

    @AllArgsConstructor
    private class Node {

        private Key key;
        private Value value;
        private Node next;
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null) {
            return;
        }
        for (Node node = first; node != null; node = node.next) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
    }

    @Override
    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        for (Node node = first; node != null; node = node.next) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public Value delete(Key key) {
        if (first == null) {
            return null;
        }
        if (first.next == null) {
            if (first.key.equals(key)) {
                Value value = first.value;
                first = null;
                return value;
            }
        }
        Node pre = first, node = pre.next;
        while (node != null) {
            if (node.key.equals(key)) {
                pre.next = node.next;
                Value value = node.value;
                node = null;
                return value;
            }
            pre = node;
            node = node.next;
        }
        return null;
    }

    @Override
    public boolean contains(Key key) {
        for (Node node = first; node != null; node = node.next) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        int size = 0;
        for (Node node = first; node != null; node = node.next) {
            size++;
        }
        return size;
    }

    @Override
    public Iterator<Key> keys() {
        List<Key> keys = new ArrayList<>();
        for (Node node = first; node != null; node = node.next) {
            keys.add(node.key);
        }
        return keys.iterator();
    }

}
