package cn.wpin.offer;

import lombok.Data;

import java.util.Arrays;
import java.util.Stack;

/**
 * 用两个栈实现队列
 *
 * @author wpin
 */
public class Offer06 {

    public static void main(String[] args) {

        Queue queue = new Queue();
        queue.appendTail(1);
        queue.appendTail(2);
        queue.appendTail(3);
        queue.appendTail(4);
        System.out.println(queue.deleteHead());
        queue.appendTail(5);
        System.out.println(queue);
        System.out.println(queue.deleteHead());
        System.out.println(Arrays.toString(queue.toArray()));
        queue.appendTail(6);
        System.out.println(Arrays.toString(queue.toArray()));
    }


    @Data
    static class Queue {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        public Integer deleteHead() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.add(stack1.pop());
                }
            }
            return stack2.pop();
        }

        public Integer appendTail(Integer item) {
            return stack1.push(item);
        }

        public int[] toArray() {
            int[] array = new int[stack1.size() + stack2.size()];
            int index = 0;
            for (int i = stack2.size() - 1; i >= 0; i--) {
                array[index++] = stack2.elementAt(i);
            }

            for (int i = 0; i < stack1.size(); i++) {
                array[index++] = stack1.elementAt(i);
            }
            return array;
        }


    }
}
