package cn.wpin.algorithm.labuladong.dp;

/**
 * 斐波拉契数列 ---动态规划解法
 *
 * @author wangpin
 */
public class Fibonacci {

    public static void main(String[] args) {
        int n = 20;
        int[] ints = new int[n + 1];
        System.out.println(recursive(n));
        System.out.println(recursiveMemo(n, ints));
        System.out.println(traverseArr(n));
        System.out.println(traverse(n));
    }


    /**
     * 递归方法求数列的第N个值
     * 存在问题，重复计算量非常大比如计算20的时候会计算一次18,计算19的时候也会计算一次（剪枝）
     *
     * @param n n个数字
     * @return 第N的值
     */
    private static int recursive(int n) {
        if (n == 1 | n == 2) {
            return 1;
        }
        return recursive(n - 1) + recursive(n - 2);
    }

    /**
     * 备忘录递归方法求数列的第N个值
     * 解决重复计算
     *
     * @param n n个数字
     * @return 第N的值
     */
    private static int recursiveMemo(int n, int[] memo) {
        if (n == 1 | n == 2) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = recursiveMemo(n - 1, memo) + recursiveMemo(n - 2, memo);
        return memo[n];
    }


    /**
     * 遍历解法
     * 存在问题：浪费数组空间
     *
     * @param n 第N个数
     * @return 第N个数的值
     */
    private static int traverseArr(int n) {
        int[] arr = new int[n + 1];
        arr[1] = arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    /**
     * 遍历，不使用数组，变量保存
     *
     * @param n 第N个数
     * @return 第N个数的值
     */
    private static int traverse(int n) {
        int pre = 1, second = 1;
        for (int i = 3; i <= n; i++) {
            int sum = pre + second;
            pre = second;
            second = sum;
        }
        return second;
    }


}
