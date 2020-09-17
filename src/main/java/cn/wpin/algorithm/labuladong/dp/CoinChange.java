package cn.wpin.algorithm.labuladong.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 凑零钱问题   动态规划
 *
 * @author wangpin
 */
public class CoinChange {

    private static Map<Integer, Integer> map = new HashMap<>(10);

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 114;

//        System.out.println(recursiveCoinChange(coins, amount));
        System.out.println(recursiveCoinChangeMemo(coins, amount));

        System.out.println(coinChange(coins, amount));

    }

    /**
     * 递归写法
     *
     * @param coins  硬币集合
     * @param amount 金额总数
     * @return 最少需要硬币的数量
     */
    private static int recursiveCoinChange(int[] coins, int amount) {
        if (amount <= 0) {
            return amount < 0 ? -1 : 0;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = recursiveCoinChange(coins, amount - coin);
            if (subProblem == -1) {
                continue;
            }
            res = Math.min(res, 1 + subProblem);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 递归备忘录写法。避免重复运算
     *
     * @param coins  硬币集合
     * @param amount 金额总数
     * @return 最少需要硬币的数量
     */
    private static int recursiveCoinChangeMemo(int[] coins, int amount) {
        if (map.containsKey(amount)) {
            return map.get(amount);
        }
        if (amount <= 0) {
            return amount < 0 ? -1 : 0;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = recursiveCoinChangeMemo(coins, amount - coin);
            if (subProblem == -1) {
                continue;
            }
            res = Math.min(res, 1 + subProblem);
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        map.put(amount, res);
        return res;
    }

    /**
     * 遍历解法
     *
     * @param coins  硬币集合
     * @param amount 金额
     * @return int
     */
    private static int coinChange(int[] coins, int amount) {
        //初始化dp的size为amount+1,每个元素也为amount+1
        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = amount + 1;
        }
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
