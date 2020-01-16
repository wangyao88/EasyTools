package com.mohan.project.easytools.collection.table;

import java.util.Iterator;

/**
 * 搜索表接口 无序
 *
 * @author mohan
 * @date 2018-12-04 09:24:24
 */
public interface Table<Key extends Comparable<Key>, Value> {

    /**
     * 添加
     * @param key 键
     * @param value 值
     */
    void put(Key key, Value value);

    /**
     * 获取key对应的value
     * @param key 键
     * @return 值
     */
    Value get(Key key);

    /**
     * 删除key对应的value
     * @param key 键
     * @return 值
     */
    Value delete(Key key);

    /**
     * 是否包含key
     * @param key 键
     * @return true:包含 false:不包含
     */
    boolean contains(Key key);

    /**
     * 是否为空
     * @return true:为空 false:不为空
     */
    boolean isEmpty();

    /**
     * 获取表大小
     * @return 表大小
     */
    int size();

    /**
     * 获取所有键的集合
     * @return 所有键的集合
     */
    Iterator<Key> keys();
}
