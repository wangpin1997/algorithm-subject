package cn.wpin.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回其中序遍历
 *
 * @author wangpin
 */
public class Leetcode94 {

    static List<Integer> integers = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        madeTree(treeNode);
        inorderTraversal(treeNode);
        System.out.println();
        inorderTraversal2(treeNode);
        System.out.println();
        inorderTraversal3(treeNode);
        System.out.println();
        System.out.println(integers);
        inorderTraversal0(treeNode);
    }

    /**
     *        A
     *      /  \
     *     B    C
     *    /\   / \
     *   D  E F   G
     *
     *  前序：A B D E C F G
     *  中序：D B E A F C G
     *  后序：D E B F G C A
     */


    /**
     * 非递归处理
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal0(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }

    /**
     * 递归求前序
     *
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversal(TreeNode root) {
        System.out.print(root.val);
        if (root.left != null) {
            inorderTraversal(root.left);
        }
        integers.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right);
        }
        return integers;
    }

    /**
     * 递归求中序
     *
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversal2(TreeNode root) {
        if (root.left != null) {
            inorderTraversal2(root.left);
        }
        System.out.print(root.val);
        integers.add(root.val);
        if (root.right != null) {
            inorderTraversal2(root.right);
        }
        return integers;
    }

    /**
     * 递归求后序
     *
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversal3(TreeNode root) {
        if (root.left != null) {
            inorderTraversal3(root.left);
        }
        integers.add(root.val);
        if (root.right != null) {
            inorderTraversal3(root.right);
        }
        System.out.print(root.val);
        return integers;
    }


    private static void madeTree(TreeNode treeNode) {
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(7);
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
