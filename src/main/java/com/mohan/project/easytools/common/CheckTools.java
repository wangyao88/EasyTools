package com.mohan.project.easytools.common;

/**
 * 字段校验工具类
 * @author mohan
 * @date 2019-08-09 14:43:07
 */
public final class CheckTools {

    private CheckTools() {}

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}