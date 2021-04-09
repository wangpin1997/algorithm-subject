package cn.wpin.offer;

/**
 * 斐波拉契数列
 *
 * @author wpin
 */
public class Offer08 {

    public static void main(String[] args) {
        System.out.println(fib(10));
        System.out.println(fib(0));
        System.out.println(fib(26));
        System.out.println(fib2(10));
        System.out.println(fib2(0));
        System.out.println(fib2(26));
    }

    /**
     * 递归实现
     *
     * @param n 第N项
     * @return 第N项的值
     */
    private static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 | n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 循环指针实现
     *
     * @param n 第N项
     * @return 第N项的值
     */
    private static int fib2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }

        int pre = 1, next = 1;
        int current = 0;
        for (int i = 3; i <= n; i++) {
            current = pre + next;
            pre = next;
            next = current;
        }
        return current;
    }
}
