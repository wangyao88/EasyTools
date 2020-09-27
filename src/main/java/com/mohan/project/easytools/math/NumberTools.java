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

    /**
     * 寻找下一个2的幂次方
     * 例如 number = 15, 则返回16
     *
     * 第一次右移一位，然后做或运算，则前两位为1
     * 第二次右移两位，然后做或运算，则前四位为1
     * 第三次右移四位，然后做或运算，则前八位为1
     * 第二次右移八位，然后做或运算，则前十六位为1
     * 第二次右移十六位，然后做或运算，则前三十二位为1
     * 然后加一
     * 整个过程相当于将number的第二位到最后一位全置为1，然后加一，连续进位
     *
     * @param number
     * @return
     */
    public static int searchNextPowerOfTwo(int number) {
        number |= number >>>  1;
        number |= number >>>  2;
        number |= number >>>  4;
        number |= number >>>  8;
        number |= number >>> 16;
        number ++;
        return number;
    }
}