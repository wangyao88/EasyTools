package com.mohan.project.easytools.math;

/**
 * NumberTools
 *
 * @author MoHan
 * @since 2020-09-21 14:08
 */
public final class NumberTools {

    private NumberTools() {}

    /**
     * 判断是否为2的指数倍
     * @param number 待判断数字
     * @return true 是2的指数倍
     */
    public static boolean isPowerOfTwo(int number) {
        return (number & -number) == number;
    }
}