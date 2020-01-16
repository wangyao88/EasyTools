package com.mohan.project.easytools.sort;

/**
 * 希尔排序
 * 关键递增序列 性能比选择排序和插入排序快几个数量级
 * @author mohan
 * @date 2018-12-03 10:54:36
 */
public class ShellSort extends AbstractSort {

    @Override
    public void doSort(Comparable[] array) {
        int length = array.length;
        int h = 1;
        while (h < length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && less(array[j], array[j-h]); j -= h) {
                    swap(array, j, j-h);
                }
            }
            h = h / 3;
        }
    }
}
