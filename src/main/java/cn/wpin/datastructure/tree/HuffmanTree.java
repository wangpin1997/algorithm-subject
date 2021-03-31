package cn.wpin.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 哈夫曼树
 *
 * @author wpin
 */
public class HuffmanTree {


    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();
        List<HuffmanTreeNode> nodes = new ArrayList<>();
        nodes.add(huffmanTree.new HuffmanTreeNode("A", 40));
        nodes.add(huffmanTree.new HuffmanTreeNode("B", 8));
        nodes.add(huffmanTree.new HuffmanTreeNode("C", 10));
        nodes.add(huffmanTree.new HuffmanTreeNode("D", 30));
        nodes.add(huffmanTree.new HuffmanTreeNode("E", 10));
        nodes.add(huffmanTree.new HuffmanTreeNode("F", 2));

        HuffmanTreeNode root = huffmanTree.createTree(nodes);

        List<HuffmanTreeNode> list = huffmanTree.breadthFirst(root);
        for (HuffmanTreeNode node : list) {
            System.out.println(node.key);
            System.out.println(node.data);
        }

    }


    private List<HuffmanTreeNode> breadthFirst(HuffmanTreeNode root) {
        Queue<HuffmanTreeNode> queue = new LinkedList<>();
        List<HuffmanTreeNode> list = new LinkedList<>();

        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            list.add(queue.peek());
            HuffmanTreeNode node = queue.poll();

            assert node != null;
            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return list;


    }


    /**
     * @param nodes 数据集合
     * @return HuffmanTreeNode
     */
    private HuffmanTreeNode createTree(List<HuffmanTreeNode> nodes) {
        while (nodes.size() > 1) {
            quickSort(nodes);

            HuffmanTreeNode left = nodes.get(nodes.size() - 1);
            HuffmanTreeNode right = nodes.get(nodes.size() - 2);

            HuffmanTreeNode parent = new HuffmanTreeNode(null, left.key + right.key);

            parent.left = left;
            parent.right = right;

            nodes.remove(nodes.size() - 1);
            nodes.remove(nodes.size() - 1);

            nodes.add(parent);
        }
        return nodes.get(0);
    }


    private void quickSort(List<HuffmanTreeNode> nodes) {
        subSort(nodes, 0, nodes.size() - 1);
    }

    /**
     * 实现快速排序算法，用于对节点进行排序
     *
     * @param nodes 数据集合
     * @param start 最左下标
     * @param end   最右下标
     */
    private void subSort(List<HuffmanTreeNode> nodes, int start, int end) {
        if (start < end) {
            //以第一个元素作为分界值
            HuffmanTreeNode base = nodes.get(start);
            //i 从左边开始，搜索大于分界值元素的下标索引
            int i = start;
            //j 从右边开始，搜索小于分界值元素的下标索引
            int j = end + 1;
            while (true) {
                //找到大于分界值的元素的索引，或者i已经到了end处
                while (i < end && nodes.get(++i).key >= base.key) {

                }
                //找到小于分界值的元素索引，或者j已经到了start处
                while (j > start && nodes.get(--j).key <= base.key) {

                }
                if (i < j) {
                    swap(nodes, i, j);
                } else {
                    break;
                }
            }
            swap(nodes, start, j);

            //递归左子序列
            subSort(nodes, start, j - 1);

            //递归右子序列
            subSort(nodes, j + 1, end);
        }
    }

    /**
     * 将指定集合中的i和j索引处的元素交换
     *
     * @param nodes 数据list
     * @param i     交换下标
     * @param j     交换下标
     */
    private void swap(List<HuffmanTreeNode> nodes, int i, int j) {
        HuffmanTreeNode tmp;
        tmp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, tmp);
    }


    class HuffmanTreeNode implements Comparable, Cloneable {
        /**
         * 权值
         */
        private int key;

        /**
         * 左子树
         */
        private HuffmanTreeNode left;

        /**
         * 右子树
         */
        private HuffmanTreeNode right;

        /**
         * 父节点
         */
        private Object data;


        HuffmanTreeNode(Object data, int key) {
            this.key = key;
            this.data = data;
        }

        @Override
        public int compareTo(Object o) {
            return this.key - ((HuffmanTreeNode) o).key;
        }

        @Override
        public Object clone() {
            Object obj = null;
            try {
                obj = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return obj;
        }
    }
}
