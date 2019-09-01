package com.mohan.project.easytools.common;

import java.util.Optional;

/**
 * java基础类型默认值
 * @author mohan
 * @date 2019-08-09 14:36:35
 */
public final class DefaultTools {

    private DefaultTools() {}

    private static final Double DOUBLE_DEFAULT = 0d;
    private static final Float FLOAT_DEFAULT = 0f;

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> defaultValue(Class<T> type) {
        CheckTools.checkNotNull(type);
        if (type == boolean.class) {
            return Optional.of((T) Boolean.FALSE);
        } else if (type == char.class) {
            return Optional.of((T) Character.valueOf('\0'));
        } else if (type == byte.class) {
            return Optional.of((T) Byte.valueOf((byte) 0));
        } else if (type == short.class) {
            return Optional.of((T) Short.valueOf((short) 0));
        } else if (type == int.class) {
            return Optional.of((T) Integer.valueOf(0));
        } else if (type == long.class) {
            return Optional.of((T) Long.valueOf(0L));
        } else if (type == float.class) {
            return Optional.of((T) FLOAT_DEFAULT);
        } else if (type == double.class) {
            return Optional.of((T) DOUBLE_DEFAULT);
        } else {
            return Optional.empty();
        }
    }
}