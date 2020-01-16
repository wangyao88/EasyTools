package com.mohan.project.easytools.sort;

/**
 * 快速排序
 * 递归切分 每次找到一个元素 左侧子数组不大于该元素 右侧子数组不小于该元素 左右子数组排序
 * 适用于数据量大且随机性强的数组
 * @author mohan
 * @date 2018-12-03 10:25:46
 */
public class QuickSort extends AbstractSort {

    @Override
    public void doSort(Comparable[] array) {
        doSort(array, 0, array.length-1);
    }

    private void doSort(Comparable[] array, int low, int high) {
        if(low >= high) {
            return;
        }
        int j = partition(array, low, high);
        doSort(array, low, j-1);
        doSort(array, j+1, high);
    }

    private int partition(Comparable[] array, int low, int high) {
        int i = low;
        int j = high + 1;
        Comparable comparable = array[low];
        while (true) {
            while (less(array[++i], comparable)) {
                if(i == high) {
                    break;
                }
            }
            while (less(comparable, array[--j])) {
                if (j == low) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(array, i, j);
        }
        swap(array, low, j);
        return j;
    }
}
