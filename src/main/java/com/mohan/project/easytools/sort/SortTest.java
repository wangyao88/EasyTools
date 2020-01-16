package com.mohan.project.easytools.sort;

import java.util.Random;

/**
 * 排序测试类
 * @author mohan
 * @date 2018-12-03 09:25:34
 */
public class SortTest {


    public static void main(String[] args) {
        Integer[] array = init(100);
//        AbstractSort sort = new SelectionSort();
//        AbstractSort sort = new SelectionSort();
//        AbstractSort sort = new ShellSort();
//        AbstractSort sort = new QuickSort();
        AbstractSort sort = new HeapSort();


        sort.sort(array);
        sort.show(array);
    }

    private static Integer[] init(int lenght) {
        Random random = new Random();
        Integer[] array = new Integer[lenght];
        for (int i = 0; i < lenght; i++) {
            array[i] = random.nextInt(lenght*100);
        }
        return array;
    }
}
