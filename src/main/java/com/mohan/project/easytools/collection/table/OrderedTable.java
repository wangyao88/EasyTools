package com.mohan.project.easytools.collection.table;

import java.util.Iterator;

/**
 * 搜索表接口 有序
 *
 * @author mohan
 * @date 2018-12-04 09:24:24
 */
public interface OrderedTable<Key extends Comparable<Key>, Value> extends Table<Key, Value> {

    /**
     * 获取最小的键
     * @return 最小的键
     */
    Key minKey();

    /**
     * 获取最大的键
     * @return 最大的键
     */
    Key maxKey();

    /**
     * 获取小于等于key的最大键
     * @param key 键
     * @return 小于等于key的最大键
     */
    Key floorKey(Key key);

    /**
     * 获取大于等于key的最小键
     * @param key 键
     * @return 大于等于key的最小键
     */
    Key ceilingKey(Key key);

    /**
     * 获取小于key的键的数量
     * @param key 键
     * @return 小于key的键的数量
     */
    int rank(Key key);

    /**
     * 获取排名为k的键
     * @param k 排名为k
     * @return 排名为k的键
     */
    Key selectKey(int k);

    /**
     * 删除最小的键
     */
    void deleteMin();

    /**
     * 删除最大的键
     */
    void deleteMax();

    /**
     * 获取[low, high]之间的键的个数
     * @param low 起点
     * @param high 终点
     * @return [low, high]之间的键的个数
     */
    int size(Key low, Key high);

    /**
     * 获取[low, high]之间的键的集合
     * @param low 起点
     * @param high 终点
     * @return [low, high]之间的键的集合
     */
    Iterator<Key> keys(Key low, Key high);
}
