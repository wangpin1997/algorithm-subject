package cn.wpin.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 力扣19题，删除链表倒数第N个节点
 *
 * @author wangpin
 */
public class Leetcode19 {
    static int index=0;

    public static void main(String[] args) {
        ListNode listNode=new ListNode(1);
        listNode.next=new ListNode(2);
        listNode.next.next=new ListNode(3);
        listNode.next.next.next=new ListNode(4);
        listNode.next.next.next.next=new ListNode(5);

        ListNode result=removeNthFromEnd2(listNode,3);
        System.out.println(result);

    }


    /**
     * 集合来做
     * @param head
     * @param n
     * @return
     */
    private static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        int index = 1;
        Map<Integer, ListNode> map = new HashMap<>(32);
        while (node != null) {
            map.put(index++, node);
            node = node.next;
        }
        int size=map.size();
        if (n==1){
            map.get(size-1).next=null;
        }else if (n==size){
            head=head.next;
        }else {
            map.get(size-n).next=map.get(size-n+2);
        }
        return head;

    }

    /**
     * 递归来实现
     * @param head
     * @param n
     * @return
     */
    private static ListNode removeNthFromEnd2(ListNode head, int n){
        if (head==null) {
            return null;
        }
        head.next=removeNthFromEnd2(head.next, n);
        index++;
        if (n==index) {
            return head.next;
        }
        return head;
    }
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
