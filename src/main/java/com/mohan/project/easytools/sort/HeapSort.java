package com.mohan.project.easytools.sort;

/**
 * 堆排序
 * 适用 部分有序 小规模
 * @author mohan
 * @date 2018-12-04 15:54:35
 */
public class HeapSort extends AbstractSort {

    @Override
    public void doSort(Comparable[] array) {
        int length = array.length;
        for (int i = length / 2; i >= 1; i--) {
            sink(array, i, length);
        }
        while (length > 1) {
            swap(array, 1, length--);
            sink(array, 1, length);
        }
    }

    /**
     * 下沉
     *
     * @param array
     * @param index
     * @param length
     */
    private void sink(Comparable[] array, int index, int length) {
        while (2 * index <= length) {
            int j = 2 * index;
            if (j < length && less(j, j + 1)) {
                j++;
            }
            if (less(j, index)) {
                break;
            }
            swap(array, index, j);
            index = j;
        }
    }
}
