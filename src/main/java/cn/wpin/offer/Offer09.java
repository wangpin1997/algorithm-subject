package cn.wpin.offer;

/**
 * 请实现一个函数， 输入一个整数，输出该数二进制表示中1的个数。
 * 例如把9表示成二进制是1001 ，有2位是1. 因此如果输入9，该出2。
 *
 * @author wpin
 */
public class Offer09 {

    public static void main(String[] args) {
        //方法1的过程
        System.out.println(printNumber(7));
        System.out.println(6 & 7);
        System.out.println(5 & 6);
        System.out.println(4 & 5);
        System.out.println(3 & 4);

        //方法2
        System.out.println(printNumber2(7));
        System.out.println(Integer.toBinaryString(7));
        System.out.println(7 >>> 1);
    }

    /**
     * 效率更高
     *
     * @param n
     * @return
     */
    private static int printNumber(int n) {
        int result = 0;
        while (n != 0) {
            result++;
            n = (n - 1) & n;
        }
        return result;
    }

    /**
     * 利用int最大为32位，拿每一位跟1&运算，累加就可以了
     *
     * @param n
     * @return
     */
    private static int printNumber2(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += (n & 1);
            n >>>= 1;
        }
        return result;
    }
}
