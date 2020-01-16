package com.mohan.project.easytools.sort;

/**
 * 插入排序
 * 适用 部分有序 小规模
 * @author mohan
 * @date 2018-12-03 10:25:46
 */
public class InsertSort extends AbstractSort {

    @Override
    public void doSort(Comparable[] array) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && less(array[j], array[j-1]); j--) {
                swap(array, j, j-1);
            }
        }
    }
}
