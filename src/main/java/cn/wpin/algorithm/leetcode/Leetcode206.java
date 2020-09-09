package cn.wpin.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反转一个单链表。力扣206题
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * @author wpin
 */
public class Leetcode206 {

    public static void main(String[] args) {
        ListNode root = getList();

        root = reverse1(root);
        System.out.println(Arrays.toString(getValue(root).toArray()));
        root = reverse2(root);
        System.out.println(Arrays.toString(getValue(root).toArray()));
        root = reverse3(root);
        System.out.println(Arrays.toString(getValue(root).toArray()));
        System.out.println(root);
    }

    /**
     * 生成链表
     *
     * @return ListNode
     */
    private static ListNode getList() {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        return root;
    }


    /**
     * 反转链表
     * 方法1:逐个将链表的节点插入到新的链表
     *
     * @param head 链表
     * @return ListNode
     */
    private static ListNode reverse1(ListNode head) {
        //新链表的尾节点
        ListNode node = null;
        while (head != null) {
            //记录节点
            ListNode temp = head;
            //遍历后移
            head = head.next;
            //将节点放入新链表表头
            temp.next = node;
            //更新新链表表头
            node = temp;
        }
        return node;
    }

    /**
     * 复习，重写一遍
     *
     * @param head 链表
     * @return ListNode
     */
    private static ListNode reverse1_1(ListNode head) {
        ListNode node = null;
        while (head != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = node;
            node = temp;
        }
        return node;
    }


    /**
     * 三指针遍历
     *
     * @param head 链表
     * @return ListNode
     */
    private static ListNode reverse2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode n0 = null;
        ListNode n1 = head;
        ListNode n2 = head.next;
        while (n1 != null) {
            n1.next = n0;
            n0 = n1;
            n1 = n2;
            if (n2 != null) {
                n2 = n2.next;
            }
        }
        return n0;
    }

    /**
     * 复习，重写一遍
     *
     * @param head 链表
     * @return ListNode
     */
    private static ListNode reverse2_1(ListNode head) {
        ListNode p0 = null;
        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p1 != null) {
            p1.next = p0;
            p0 = p1;
            p1 = p2;
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        return p0;
    }

    /**
     * 递归遍历 反转
     *
     * @param head 链表
     * @return ListNode
     */
    private static ListNode reverse3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverse3(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * 复习，重写一遍
     *
     * @param head 链表
     * @return ListNode
     */
    private static ListNode reverse3_1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverse3_1(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * 遍历链表，把值按顺序取出存入list
     *
     * @param node 链表
     * @return List
     */
    private static List<Integer> getValue(ListNode node) {
        List<Integer> list = new ArrayList<>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return list;
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
