package com.mohan.project.easytools.sort;

/**
 * 选择排序
 * @author mohan
 * @date 2018-12-03 09:03:24
 */
public class SelectionSort extends AbstractSort {

    @Override
    public void doSort(Comparable[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int minIndex = i;
            for (int j = i+1; j < length; j++) {
                if(less(array[j], array[minIndex])) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }
}
