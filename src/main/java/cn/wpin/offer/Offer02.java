package cn.wpin.offer;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请
 * 完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
 *
 * @author wpin
 */
public class Offer02 {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 8, 9},
                {12, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        System.out.println(find(arr, 10));
        System.out.println(find(arr, 11));
        System.out.println(find(arr, 14));
        System.out.println(find(arr, 18));
        System.out.println("----------------");
        System.out.println(find(10, arr));
        System.out.println(find(11, arr));
        System.out.println(find(14, arr));
        System.out.println(find(18, arr));

    }


    /**
     * 笨方法，双层遍历
     *
     * @param arr 二维数组
     * @param num 数字
     * @return boolean
     */
    private static boolean find(int[][] arr, int num) {
        for (int[] ints : arr) {
            //等于直接返回正确，大于返回错误，小于就再往下比较
            if (ints[0] == num) {
                return true;
            } else if (ints[0] < num) {
                for (int anInt : ints) {
                    if (anInt == num) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean find(int num, int[][] arr) {
        //判断数组有没有元素，没有则肯定是false
        if (arr == null || arr.length < 1 || arr[0].length < 1) {
            return false;
        }
        //数组的列数
        int col = arr.length;
        //数组的行数
        int row = arr[0].length;
        //起始开始的行数
        int startRow = 0;
        //起始开始的列数
        int startColumn = col - 1;
        //确保要查找的数字确实在数组内
        while (startRow >= 0 && startRow < row && startColumn >= 0 && startColumn < col) {
            //如果找到直接返回true
            if (arr[startRow][startColumn] == num) {
                return true;
                //如果找到的数字比要要找的数字大，则后往前遍历这个横轴数组，一直--
            } else if (arr[startRow][startColumn] > num) {
                startColumn--;
            } else {
                //行数++
                startRow++;
            }
        }
        return false;
    }


}
