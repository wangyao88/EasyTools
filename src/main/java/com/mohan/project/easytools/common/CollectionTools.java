package com.mohan.project.easytools.common;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import lombok.Cleanup;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 集合相关工具类
 * @author mohan
 * @date 2019-07-08
 */
public class CollectionTools {

    public static <T> T getOneElementOrNull(List<T> lists) {
        if (null != lists && lists.size() >0) {
            return lists.get(0);
        } else {
            return null;
        }
    }

    public static <T> Optional<T> getFirstElement(Collection<T> collection) {
        if (isNotEmpty(collection)) {
            return collection.stream().findFirst();
        } else {
            return Optional.empty();
        }
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    public static <T> Collection<T> nullToEmpty(Collection<T> collection) {
        return isEmpty(collection) ? Lists.newArrayList() : collection;
    }

    public static <T> Optional<Collection<T>> optional(Collection<T> src) {
        if(isEmpty(src)) {
            return Optional.empty();
        }
        return Optional.of(src);
    }

    public static <T> Optional<Collection<T>> depCopy(Collection<T> src) {
        if(isEmpty(src)) {
            return Optional.empty();
        }
        try {
            @Cleanup
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            @Cleanup
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(src);
            @Cleanup
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bos.toByteArray());
            @Cleanup
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            return Optional.ofNullable((Collection<T>) ois.readObject());
        } catch (Exception e) {
            System.out.println("集合深拷贝失败！完整错误信息：\n" + Throwables.getStackTraceAsString(e));
            return Optional.empty();
        }
    }
}
