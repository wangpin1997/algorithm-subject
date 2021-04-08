package cn.wpin.offer;

import cn.wpin.algorithm.common.TreeNode;

/**
 * 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果
 * 中都不含重复的数字。例如：前序遍历序列｛ 1， 2， 4，7，3，5，6，8｝和中序遍历序
 * 列｛4，7，2，1，5，3，8，6}，重建出下图所示的二叉树并输出它的头结点。
 *
 * @author wpin
 */
public class Offer05 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //     *          1
        //     *         / \
        //     *        2   3
        //     *       /   / \
        //     *      4   5   6
        //     *      \      /
        //     *       7    8
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};

        int[] mid = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode construct = construct(pre, mid);
        printTree(construct);

    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二节树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *
     * @param pre 谦虚遍历
     * @param mid 中序遍历
     * @return 根节点
     */
    private static TreeNode construct(int[] pre, int[] mid) {
        if (pre == null || mid == null || pre.length != mid.length || mid.length == 0) {
            return null;
        }
        return construct(pre, 0, pre.length - 1, mid, 0, mid.length - 1);

    }

    /**
     * @param pre 前序遍历
     * @param ps  前序遍历开始位置
     * @param pe  前序遍历结束位置
     * @param mid 中序遍历
     * @param ms  中序遍历开始位置
     * @param me  中序遍历结束位置
     * @return 树的根节点
     */
    private static TreeNode construct(int[] pre, int ps, int pe, int[] mid, int ms, int me) {
        //开始位置大于结束位置说明没有需要处理的元素
        if (ps > pe) {
            return null;
        }
        //取前序遍历的第一个数字，是根节点
        int value = pre[ps];

        int index = ms;
        //在中序遍历中找到根节点所在位置
        while (index < me && mid[index] != value) {
            index++;
        }
        //如果在中序遍历中没有找到，说明输入的参数不合法
        if (index > me) {
            throw new RuntimeException("invalid input");
        }
        //创建当前根节点树，并赋值
        TreeNode node = new TreeNode();

        node.val = value;

        //递归左子树，左子树元素个数：index-ms+1个
        //左子树对应的前序遍历位置在[ps+1,ps+index-ms]
        //左子树对应的中序遍历的位置在[ms, index-1]
        node.left = construct(pre, ps + 1, ps + index - ms, mid, ms, index - 1);

        //递归右子树，右子树元素个数：me-index
        //右子树对应的前序遍历的位置在[ps+index-is+1, pe]
        //右子树对应的中序遍历的位置在[index+1, me]
        node.right = construct(pre, ps + index - ms + 1, pe, mid, index + 1, me);

        return node;

    }

    private static void printTree(TreeNode node) {
        if (node != null) {
            printTree(node.left);
            System.out.println(node.val);
            printTree(node.right);
        }
    }
}
