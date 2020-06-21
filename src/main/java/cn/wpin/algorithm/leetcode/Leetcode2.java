package cn.wpin.algorithm.leetcode;

/**
 * 力扣打卡第二天2020-04-14
 * 力扣第二题，两数相加
 * @author wangpin
 */
public class Leetcode2 {


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1), pre = dummyHead;
        int t = 0;
        while (l1 != null || l2 != null || t != 0) {
            if (l1 != null) {
                t += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                t += l2.val;
                l2 = l2.next;
            }
            pre.next = new ListNode(t % 10);
            pre = pre.next;
            t /= 10;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode s1 = new ListNode(7);
        s1.next = new ListNode(1);
        s1.next.next = new ListNode(3);

        ListNode s2 = new ListNode(9);
        s2.next = new ListNode(1);
        s2.next.next = new ListNode(7);
        ListNode result = node(s1, s2);
        System.out.println(result.toString());

    }


    private static ListNode node(ListNode a, ListNode b) {
        ListNode node = new ListNode(0), pre = node;
        int x = 0;
        while (a != null | b != null | x != 0) {
            if (a != null) {
                x += a.val;
                a = a.next;
            }
            if (b != null) {
                x += b.val;
                b = b.next;
            }
            pre.next = new ListNode(x % 10);
            pre = pre.next;
            x /= 10;
        }
        return node.next;
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

