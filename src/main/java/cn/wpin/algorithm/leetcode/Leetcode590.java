package cn.wpin.algorithm.leetcode;

import java.util.*;

/**
 * N叉树的后序遍历
 *
 * @author wangpin
 */
public class Leetcode590 {

    public static void main(String[] args) {

        Node root = madeNode();
        System.out.println();
        System.out.println(postorder(root));

    }


    /**
     * 思想，先求出根右左--》然后倒序即可
     * 步骤：
     * 1. 1 --> 进入栈中   stack[1]
     * 2. 1出栈。并吧val放到list中，遍历1.的孩子  1的孩子 2--3--4依次进栈   stack [2,3,4]
     * 3. 4出栈-->子集为空，直接返回   list.add(4)[1,4]  stack[2,3]
     * 4. 3出栈 -->子集为空  直接返回  list.add(3) [1,4,3]  stack[2]
     * 5. 2出栈 --->子集不为空，孩子遍历入栈 list.add(2)[1,4,3,2] stack[5,6,7]
     * 6. 7出栈---> 子集为空，直接返回  list.add(7)[1,4,3,2,7]  stack[5,6]
     * 7. 6出栈---> 子集为空，直接返回  list.add(6)[1,4,3,2,7,6]  stack[5
     * 8. 5出栈---> 子集为空，直接返回  list.add(5)[1,4,3,2,7,6,5]  stack[]
     * 9. stack为空结束while循环 再讲 list倒序,得到 [5,6,7,2,3,4,1]
     *
     * @param root
     * @return
     */

    private static List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(node.val);
            if (node.children != null) {
                for (Node n : node.children) {
                    stack.push(n);
                }
            }
        }
        System.out.println(result);
        Collections.reverse(result);
        return result;
    }

    private static Node madeNode() {
        Node left = new Node(2);
        Node center = new Node(3);
        Node right = new Node(4);
        List<Node> children = new LinkedList<>();
        children.add(left);
        children.add(center);
        children.add(right);
        List<Node> children2 = new LinkedList<>();
        Node left2 = new Node(5);
        Node center2 = new Node(6);
        Node right2 = new Node(7);
        children2.add(left2);
        children2.add(center2);
        children2.add(right2);
        left.children = children2;
        return new Node(1, children);
    }


    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}
