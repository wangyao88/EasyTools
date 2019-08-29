package com.mohan.project.easytools.common;

import java.util.stream.Stream;

/**
 * Object相关工具类
 * @author mohan
 * @date 2019-07-08
 */
public class ObjectTools {

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }


    public static boolean isAllNull(Object... obj) {
        return Stream.of(obj).allMatch(ObjectTools::isNull);
    }

    public static boolean isAllNotNull(Object... obj) {
        return Stream.of(obj).allMatch(ObjectTools::isNotNull);
    }

    public static boolean isAnyNull(Object... obj) {
        return Stream.of(obj).anyMatch(ObjectTools::isNull);
    }

    public static boolean equalsIgnoreNull(Object left, Object right) {
        return isAllNull(left, right) || isAllNotNull(left, right) && left.equals(right);
    }
}
