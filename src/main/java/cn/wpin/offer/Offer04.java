package cn.wpin.offer;

import cn.wpin.algorithm.common.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 从尾到头打印链表
 *
 * @author wpin
 */
public class Offer04 {

    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        node(head);
        System.out.println(result);
        node2(head);

    }

    /**
     * 递归实现
     *
     * @param head
     */
    private static void node(ListNode head) {
        if (head == null) {
            return;
        }
        node(head.next);
        result.add(head.val);
    }

    /**
     * 使用栈
     *
     * @param head
     */
    private static void node2(ListNode head) {
        Stack<Integer> result = new Stack<>();
        while (head != null) {
            result.push(head.val);
            head = head.next;
        }
        while (result.size() > 0) {
            System.out.println(result.pop());
        }
    }


}
