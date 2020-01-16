package com.mohan.project.easytools.collection.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * 二分查找实现的搜索表 有序
 *
 * @author mohan
 * @date 2018-12-04 09:24:24
 */
public class BinarySearchOrderedTable<Key extends Comparable<Key>, Value> implements OrderedTable<Key, Value> {

    private Key[] keys;
    private Value[] values;
    private int size;

    private static final int DEFAULT_CAPACITY = 16;

    public BinarySearchOrderedTable() {
        this(DEFAULT_CAPACITY);
    }

    public BinarySearchOrderedTable(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Comparable[capacity];
    }

    @Override
    public void put(Key key, Value value) {
        expandCapacity();
        int count = rank(key);
        if (count < size && key.compareTo(keys[count]) == 0) {
            values[count] = value;
            return;
        }
        for (int i = size; i > count; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[count] = key;
        values[count] = value;
        size++;
    }

    @Override
    public Value get(Key key) {
        if (key == null || isEmpty()) {
            return null;
        }
        int count = rank(key);
        if (count < size && key.compareTo(keys[count]) == 0) {
            return values[count];
        }
        return null;
    }

    @Override
    public Value delete(Key key) {
        if (key == null || isEmpty()) {
            return null;
        }
        int count = rank(key);
        if (count < size && key.compareTo(keys[count]) == 0) {
            Value value = values[count];
            int numMoved = 1;
            System.arraycopy(keys, count + 1, keys, count, numMoved);
            keys[size - 1] = null;
            System.arraycopy(values, count + 1, values, count, numMoved);
            values[size - 1] = null;
            size--;
            cutCapacity();
            return value;
        }
        return null;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Key minKey() {
        if (isEmpty()) {
            return null;
        }
        return keys[0];
    }

    @Override
    public Key maxKey() {
        if (isEmpty()) {
            return null;
        }
        return keys[size - 1];
    }

    @Override
    public Key floorKey(Key key) {
        if (isEmpty() || size == 1) {
            return null;
        }
        int count = rank(key);
        if (count == 0) {
            return null;
        }
        return selectKey(count - 1);
    }

    @Override
    public Key ceilingKey(Key key) {
        if (isEmpty() || size == 1) {
            return null;
        }
        int count = rank(key);
        if (count == size - 1) {
            return null;
        }
        return selectKey(count + 1);
    }

    @Override
    public int rank(Key key) {
        if (isEmpty()) {
            return 0;
        }
        int low = 0, high = size - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            int compare = key.compareTo(keys[middle]);
            if (compare < 0) {
                high = middle - 1;
            } else if (compare > 0) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return low;
    }

    @Override
    public Key selectKey(int k) {
        if (k > size - 1) {
            return null;
        }
        return keys[k];
    }

    @Override
    public void deleteMin() {
        delete(minKey());
    }

    @Override
    public void deleteMax() {
        delete(maxKey());
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int size(Key low, Key high) {
        if (high.compareTo(low) < 0) {
            return 0;
        } else if (contains(high)) {
            return rank(high) - rank(low) + 1;
        } else {
            return rank(high) - rank(low);
        }
    }

    @Override
    public Iterator<Key> keys() {
        if (isEmpty()) {
            return new ArrayList<Key>().iterator();
        }
        return Arrays.stream(keys).collect(Collectors.toList()).iterator();
    }

    @Override
    public Iterator<Key> keys(Key low, Key high) {
        if (isEmpty()) {
            return new ArrayList<Key>().iterator();
        }
        int lowIndex = rank(low);
        int highIndex = rank(high);
        return Arrays.stream(keys).skip(lowIndex).limit(highIndex - lowIndex).collect(Collectors.toList()).iterator();
    }

    private void expandCapacity() {
        int oldCapacity = keys.length;
        if(oldCapacity == Integer.MAX_VALUE) {
            throw new RuntimeException("到达最大容量，无法扩容！");
        }
        if (size == oldCapacity) {
            int increment = oldCapacity >> 1;
            int newCapacity;
            if (oldCapacity > Integer.MAX_VALUE - increment) {
                newCapacity = Integer.MAX_VALUE;
            } else {
                newCapacity = oldCapacity + increment;
            }
            keys = Arrays.copyOf(keys, newCapacity);
            values = Arrays.copyOf(values, newCapacity);
        }
    }

    private void cutCapacity() {

    }
}
