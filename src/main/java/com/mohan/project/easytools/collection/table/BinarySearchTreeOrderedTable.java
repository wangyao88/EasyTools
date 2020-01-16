package com.mohan.project.easytools.collection.table;

import java.util.*;

/**
 * 二叉查找树实现的搜索表 有序
 *
 * @author mohan
 * @date 2018-12-05 16:50:08
 */
public class BinarySearchTreeOrderedTable<Key extends Comparable<Key>, Value> implements OrderedTable<Key, Value> {

    /**
     * 根节点
     */
    private Node root;

    private class Node {

        private Key key;
        private Value value;
        private Node left;
        private Node right;
        /**
         * 以该节点为根的子树中的节点总数
         */
        private int count;

        public Node(Key key, Value value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
        }
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    @Override
    public Value delete(Key key) {
        root = deleteNode(root, key);
        return null;
    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public int size(Key low, Key high) {
        return 0;
    }

    @Override
    public Iterator<Key> keys() {
        return keys(minKey(), maxKey());
    }

    @Override
    public Iterator<Key> keys(Key low, Key high) {
        Collection<Key> collection = new LinkedList<>();
        keys(root, collection, low, high);
        return (Iterator<Key>) collection;
    }

    @Override
    public Key minKey() {
        Node node = minNode(root);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Override
    public Key maxKey() {
        Node node = maxNode(root);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Override
    public Key floorKey(Key key) {
        Node node = floorNode(root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Override
    public Key ceilingKey(Key key) {
        Node node = ceilingNode(root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Override
    public int rank(Key key) {
        return rankNode(root, key);
    }

    @Override
    public Key selectKey(int k) {
        Node node = selectNode(root, k);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    @Override
    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        if (node.key == null || key == null) {
            return node;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = put(node.left, key, value);
        } else if (compare > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Value get(Node node, Key key) {
        if (node == null || node.key == null || key == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return get(node.left, key);
        } else if (compare > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    private Node deleteNode(Node node, Key key) {
        if(node == null || key == null) {
            return node;
        }
        int compare = key.compareTo(node.key);
        if(compare < 0) {
            node.left = deleteNode(node.left, key);
        }else if(compare > 0) {
            node.right = deleteNode(node.right, key);
        }else {
            if(node.left == null) {
                return node.right;
            }
            if(node.right == null) {
                return node.left;
            }
            Node temp = node;
            node = minNode(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    private void keys(Node node, Collection<Key> collection, Key low, Key high) {
        if(node == null) {
            return;
        }
        if(low == null) {
            low = minKey();
        }
        if(high == null) {
            high = maxKey();
        }
        int lowCompare = low.compareTo(node.key);
        int highCompare = high.compareTo(node.key);
        if(lowCompare < 0) {
            keys(node.left, collection, low, high);
        }
        if(lowCompare <= 0 && highCompare >= 0) {
            collection.add(node.key);
        }
        if(highCompare > 0) {
            keys(node.right, collection, low, high);
        }
    }

    private Node minNode(Node node) {
        if (node == null) {
            return null;
        }
        Node leftNode = node.left;
        if (leftNode == null) {
            return node;
        }
        return minNode(leftNode);
    }

    private Node maxNode(Node node) {
        if (node == null) {
            return null;
        }
        Node rightNode = node.right;
        if (rightNode == null) {
            return node;
        }
        return maxNode(rightNode);
    }

    private Node floorNode(Node node, Key key) {
        if (node == null || key == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node;
        }
        if (compare < 0) {
            return floorNode(node.left, key);
        }
        Node right = floorNode(node.right, key);
        if (right != null) {
            return right;
        }
        return node;
    }

    private Node ceilingNode(Node node, Key key) {
        if (node == null || key == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node;
        }
        if (compare > 0) {
            return floorNode(node.right, key);
        }
        Node left = floorNode(node.left, key);
        if (left != null) {
            return left;
        }
        return node;
    }

    private Node selectNode(Node node, int k) {
        if (node == null) {
            return null;
        }
        int count = size(node);
        if (count > k) {
            return selectNode(node.left, k);
        } else if (count < k) {
            return selectNode(node.right, k - count - 1);
        } else {
            return node;
        }
    }

    private int rankNode(Node node, Key key) {
        if(node == null || key == null) {
            return 0;
        }
        int compare = key.compareTo(node.key);
        if(compare < 0) {
            return rankNode(node.left, key);
        }else if(compare > 0) {
            return 1 + size(node.left) + rankNode(node.right, key);
        }else {
            return size(node.left);
        }
    }

    private Node deleteMin(Node node) {
        if(node == null) {
            return null;
        }
        if(node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node deleteMax(Node node) {
        if(node == null) {
            return null;
        }
        if(node.right == null) {
            return node.left;
        }
        node.right = deleteMax(node.right);
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    public static void main(String[] args) {
        BinarySearchTreeOrderedTable<Integer, Integer> table = new BinarySearchTreeOrderedTable<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            table.put(random.nextInt(10000), random.nextInt(10000));
        }
        System.out.println(table.size());
        System.out.println(table.rank(table.maxKey()));
    }
}
