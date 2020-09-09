package cn.wpin.algorithm.leetcode;

/**
 * 力扣打卡第二天2020-04-14
 * 力扣第二题，两数相加
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wangpin
 * review on 2020-08-26
 */
public class Leetcode2 {


    public static void main(String[] args) {
        System.out.println(15/10);
        System.out.println(15%10);

        //倒序相加317+719=1036
        ListNode s1 = new ListNode(7);
        s1.next = new ListNode(1);
        s1.next.next = new ListNode(3);

        ListNode s2 = new ListNode(9);
        s2.next = new ListNode(1);
        s2.next.next = new ListNode(7);
        ListNode result = add(s1, s2);
        System.out.println(result.toString());

    }


    /**
     * 两数相加,思想：相加%10取模，多/10的向前进，继续相加
     *
     * @param l1
     * @param l2
     * @return
     */
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


    private static ListNode add(ListNode x, ListNode y) {
        ListNode node = new ListNode(0), pre = node;
        int t = 0;

        while (x != null | y != null | t != 0) {
            if (x != null) {
                t += x.val;
                x = x.next;
            }
            if (y != null) {
                t += y.val;
                y = y.next;
            }
            pre.next = new ListNode(t % 10);
            pre = pre.next;
            t /= 10;
        }
        return node.next;
    }

    private static ListNode node(ListNode a, ListNode b) {
        ListNode node = new ListNode(0), pre = node;
        int sum = 0;
        while (a != null | b != null | sum != 0) {
            if (a != null) {
                sum += a.val;
                a = a.next;
            }
            if (b != null) {
                sum += b.val;
                b = b.next;
            }
            pre.next = new ListNode(sum % 10);
            pre = pre.next;
            sum /= 10;
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

