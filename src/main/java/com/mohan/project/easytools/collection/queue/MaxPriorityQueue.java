package com.mohan.project.easytools.collection.queue;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 优先级队列
 * 二叉树 有序 自上而下依次减小
 *
 * @author mohan
 * @date 2018-12-04 09:24:24
 */
public class MaxPriorityQueue<Value extends Comparable<Value>> {

    /**
     * 基于堆的完全二叉树
     */
    private Value[] queue;

    @Getter
    private int depth = 0;

    @Getter
    private int maxSize;

    private int currentSize = 0;

    private static final int DEFAULT_MAX_SIZE = 14;

    private static final List<Range> ranges = Lists.newArrayList();

    public MaxPriorityQueue() {
        this(DEFAULT_MAX_SIZE);
    }

    public MaxPriorityQueue(int maxSize) {
        this.maxSize = maxSize + 1;
        queue = (Value[]) new Comparable[this.maxSize];
        initRanges();
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int size() {
        return currentSize;
    }

    public void push(Value value) {
        ensurePushCapacity();
        queue[++currentSize] = value;
        swim(currentSize);
    }

    public Value pollMax() {
        if (currentSize == 0) {
            return null;
        }
        Value value = queue[1];
        swap(1, currentSize--);
        queue[currentSize + 1] = null;
        sink(1);
        ensurePollCapacity();
        return value;
    }

    public Value pollMin() {
        if (currentSize == 0) {
            return null;
        }
        Value value = queue[currentSize--];
        queue[currentSize + 1] = null;
        ensurePollCapacity();
        return value;
    }

    /**
     * 上浮
     *
     * @param index
     */
    private void swim(int index) {
        while (index > 1 && less(index / 2, index)) {
            swap(index / 2, index);
            index /= 2;
        }
    }

    /**
     * 下沉
     *
     * @param index
     */
    private void sink(int index) {
        while (2 * index <= currentSize) {
            int j = 2 * index;
            if (j < currentSize && less(j, j + 1)) {
                j++;
            }
            if (less(j, index)) {
                break;
            }
            swap(index, j);
            index = j;
        }
    }

    private boolean less(int left, int right) {
        return queue[left].compareTo(queue[right]) < 0;
    }

    private void swap(int left, int right) {
        Value value = queue[left];
        queue[left] = queue[right];
        queue[right] = value;
    }

    private void setDepth(int index) {
        Optional<Range> rangeOptional = ranges.stream().filter(range -> range.getLow() <= index && index <= range.getHigh()).findFirst();
        if (rangeOptional.isPresent()) {
            depth = rangeOptional.get().getDepth();
            return;
        }
        throw new RuntimeException("MaxPriorityQueue 最大深度为31，已超出，无法添加新的元素");
    }

    private void ensurePushCapacity() {
        int nextSize = currentSize + 1;
        setDepth(nextSize);
        if (nextSize == maxSize) {
            maxSize += (1 << depth) + 1;
            queue = Arrays.copyOf(queue, maxSize);
        }
    }

    private void ensurePollCapacity() {
        setDepth(currentSize);
        int size = 1 << depth;
        if (currentSize+1 == size) {
            maxSize = size;
            queue = Arrays.copyOf(queue, maxSize);
        }
    }

    private void initRanges() {
        for (int i = 0; i < 32; i++) {
            ranges.add(new Range(i));
        }
    }

    @Data
    private static class Range {

        private int depth;
        private int low;
        private int high;

        Range(int depth) {
            this.depth = depth;
            this.setLow(1 << (depth - 1));
            this.setHigh((1 << depth) - 1);
        }
    }
}
