package cn.wpin.algorithm.leetcode;

import java.io.Serializable;

/**
 * 旋转链表，力扣61题
 *
 * @author wangpin
 */
public class Leetcode61 implements Serializable {

    static int x=0;

    static int size=0;

    static ListNode result;


    public static void main(String[] args) {
       ListNode listNode=new ListNode(1);
        listNode.next=new ListNode(2);
        listNode.next.next=new ListNode(3);
        listNode.next.next.next=new ListNode(4);
        listNode.next.next.next.next=new ListNode(5);
//        ListNode node=rotateRight(listNode);
        ListNode node1=reverseN(listNode,2);
        System.out.println(node1);
        
    }



//    private static ListNode rotateRight(ListNode head, int k) {
//        ListNode node=head;
//        if (head == null) {
//            return null;
//        }
//        if (size==0){
//            while (node!=null){
//                size++;
//                node=node.next;
//            }
//        }
//        if (size<k){
//            k=k-size;
//        }
//        head.next=rotateRight(head.next, k);
//        x++;
//        if (k==x){
////            re=head;
//            return head;
//        }
//        re.next=head;
//        return re.next;
//
//    }

    private static ListNode rotateRight(ListNode head){
        if (head.next==null) {
            return head;
        }
        ListNode last=rotateRight(head.next);
        head.next.next=head;
        head.next=null;
        return last;
    }
   private static ListNode reverseN(ListNode head, int n) {
        if (n == 0) {
            // 记录第 n + 1 个节点
            result = head.next;
            return result;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
//        head.next = result;
        return last;
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
