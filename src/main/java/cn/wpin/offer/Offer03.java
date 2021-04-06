package cn.wpin.offer;

/**
 * 替换空格
 *
 * @author wpin
 */
public class Offer03 {

    public static void main(String[] args) {
        String str = "we are family";
        System.out.println(replace(str));
        System.out.println(replace2(str));

    }

    private static String replace(String str) {
        return str.replace(" ", "%20");
    }

    private static String replace2(String str) {
        StringBuilder result = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (aChar == ' ') {
                result.append("%20");
            } else {
                result.append(aChar);
            }
        }
        return result.toString();
    }
}
