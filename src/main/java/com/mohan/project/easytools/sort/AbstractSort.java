package com.mohan.project.easytools.sort;

/**
 * 排序抽象类
 * @author mohan
 * @date 2018-12-03 09:03:24
 */
public abstract class AbstractSort implements Sort {

    public void sort(Comparable[] array) {
        long start = System.currentTimeMillis();
        doSort(array);
        long end = System.currentTimeMillis();
        System.out.println("sorted cost time: " + (end-start) + "(ms)");
    }

    protected boolean less(Comparable left, Comparable right) {
        return left.compareTo(right) < 0;
    }

    protected void swap(Comparable[] array, int a, int b) {
        Comparable temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public void show(Comparable[] array) {
        for (Comparable comparable : array) {
            System.out.print(comparable + " ");
        }
        System.out.println();
    }

    public boolean isSorted(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            if(less(array[i+1], array[i])) {
                return false;
            }
        }
        return true;
    }
}
