package cn.wpin.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <br>两数之和<b/>
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * @author wangpin
 * 力扣第一题，两数之和
 *
 * review on 2020-08-26
 */
public class Leetcode1 {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 5, 6, 7, 9, 21, 432};
        int target = 9;
        //遍历次数为3次
        System.out.println(Arrays.toString(new Leetcode1().twoSum(arr, target)));
        //遍历次数为6次
        System.out.println(Arrays.toString(new Leetcode1().twoNum(arr, target)));

    }

    /**
     * 一次遍历，定义一个hashMap<Integer,Integer>，补数为键，下标为值。
     * 遍历到后面碰到包含的，就是找到了
     *
     * @param nums   数组
     * @param target 目标值
     * @return int[]
     */
    private int[] twoSum(int[] nums, int target) {
        int[] indexArr = new int[2];
        int count = 0;
        // 建立k-v ，一一对应的哈希表
        Map<Integer, Integer> hash = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            count++;
            if (hash.containsKey(nums[i])) {
                indexArr[0] = i;
                indexArr[1] = hash.get(nums[i]);
                System.out.println(count);
                return indexArr;
            }
            // 将数据存入 key为补数 ，value为下标
            hash.put(target - nums[i], i);
        }

        return indexArr;
    }

    /**
     * 两数字之和，双重遍历，笨方法，不推荐
     *
     * @param num    数组
     * @param target 结果数字
     * @return int[]
     */
    private int[] twoNum(int[] num, int target) {
        int count = 0;
        int[] array = new int[2];
        for (int i = 0; i < num.length; i++) {
            for (int j = num.length - 1; j > i; j--) {
                count++;
                if (num[i] + num[j] == target) {
                    array[0] = i;
                    array[1] = j;
                    System.out.println(count);
                    return array;
                }
            }
        }
        return array;
    }

}
