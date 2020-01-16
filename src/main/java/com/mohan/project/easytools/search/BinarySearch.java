package com.mohan.project.easytools.search;

import java.util.Arrays;

/**
 * 二分查找
 *
 * @author mohan
 */
public class BinarySearch {

    /**
     * 二分查找 返回key在array数组中的索引
     *
     * @param key
     * @param array
     * @return -1 未找到
     */
    public static int search(Comparable key, Comparable[] array) {
        Arrays.sort(array);
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (key.compareTo(array[middle]) < 0) {
                high = middle - 1;
            } else if (key.compareTo(array[middle]) > 0) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
