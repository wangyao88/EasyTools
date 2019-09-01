package com.mohan.project.easytools.common;

import java.lang.reflect.Array;

public final class ArrayTools {

    private ArrayTools() {}

    public static boolean isEmpty(final Object[] array) {
        return getLength(array) == 0;
    }

    public static boolean isNotEmpty(final Object[] array) {
        return isEmpty(array);
    }

    public static int getLength(final Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }
}