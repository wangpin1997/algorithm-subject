package cn.wpin.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 力扣第77题,给定两个整数n和k,返回1-n中所有可能出现的k的个数集合
 * 如 n=4,k=2  输出 [1,2],[1,3],[1,4],[2,3],[2,4],[3,4]
 *
 * @author wangpin
 */
public class Leetcode77 {

    private List<List<Integer>> result;
    private List<Integer> tmp;

    public static void main(String[] args) {
        Leetcode77 leetcode77=new Leetcode77();
        leetcode77.combine(4, 2);
        System.out.println(leetcode77.result);
    }

    public List<List<Integer>> combine(int n, int k) {
        result = new ArrayList<>();
        tmp = new ArrayList<>();
        backTrace(k, 0, n);
        return result;
    }

    private void backTrace(int remain, int last, int n) {
        if (remain == 0) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        // n-remain+1这里是剪枝的关键，25ms   到 1ms
        for (int i = last + 1; i <= n - remain + 1; i++) {
            tmp.add(i);
            backTrace(remain - 1, i, n);
            tmp.remove(tmp.size() - 1);
        }
    }
}
