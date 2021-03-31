package cn.wpin.datastructure.tree.huffmzip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;

/**
 * 哈夫曼编码压缩类
 *
 * @author wpin
 */
public class Compress {

    public int[] times = new int[256];
    public String[] huffmCodes = new String[256];
    public LinkedList<HuffmNode> list = new LinkedList<>();

    /**
     * 初始化
     */
    public Compress() {
        for (int i = 0; i < huffmCodes.length; i++) {
            huffmCodes[i] = "";
        }
    }

    /**
     * 构建哈夫曼树
     *
     * @return
     */
    public HuffmNode createTree() {
        //将次数当为权值，构造森林
        for (int i = 0; i < times.length; i++) {
            if (times[i] != 0) {
                HuffmNode n = new HuffmNode(times[i], i);
                list.add(n);
            }
        }

        while (list.size() > 1) {
            HuffmNode firstNode = list.removeFirst();

            HuffmNode secondNode = list.removeFirst();

            HuffmNode fatherNode = new HuffmNode(firstNode.getData() + secondNode.getData(), -1);

            fatherNode.setLeft(firstNode);

            fatherNode.setRight(secondNode);

            list.add(getIndex(fatherNode), fatherNode);

        }
        return list.getFirst();
    }

    /**
     * 利用前序遍历获取编码表
     *
     * @param root
     * @param code
     */
    public void getHuffmCode(HuffmNode root, String code) {
        if (root.getLeft() != null) {
            getHuffmCode(root.getLeft(), code + "0");
        }
        if (root.getRight() != null) {
            getHuffmCode(root.getRight(), code + "1");
        }
        if (root.getLeft() == null && root.getRight() == null) {
            huffmCodes[root.getIndex()] = code;
        }

    }

    /**
     * 压缩文件
     *
     * @param srcPath  文件起始目录
     * @param destPath 目标目录
     * @throws Exception
     */
    public void compress(String srcPath, String destPath) throws Exception {
        FileInputStream srcFis = new FileInputStream(srcPath);
        FileOutputStream destFos = new FileOutputStream(destPath);

        int value = srcFis.read();
        StringBuilder str = new StringBuilder();
        while (value != -1) {
            str.append(huffmCodes[value]);
            value = srcFis.read();
        }
        System.out.println(str);
        srcFis.close();
        String s;
        while (str.length() >= 8) {
            s = str.substring(0, 8);
            int b = changeStringToInt(s);
            destFos.write(b);
            destFos.flush();
            str = new StringBuilder(str.substring(8));
        }

        int last1 = 8 - str.length();
        for (int i = 0; i < last1; i++) {
            str.append("0");
        }
        s = str.substring(0, 8);
        int d = changeStringToInt(s);
        destFos.write(d);


        destFos.close();


    }

    /**
     * 插入元素位置的索引
     *
     * @param node
     * @return
     */
    private int getIndex(HuffmNode node) {
        for (int i = 0; i < list.size(); i++) {
            if (node.getData() <= list.get(i).getData()) {
                return i;
            }
        }
        return list.size();
    }

    /**
     * 字符串转换成整数
     *
     * @param s 字符串参数
     * @return
     */
    public int changeStringToInt(String s) {
        int v1 = (s.charAt(0) - 48) * 128;
        int v2 = (s.charAt(1) - 48) * 64;
        int v3 = (s.charAt(2) - 48) * 32;
        int v4 = (s.charAt(3) - 48) * 16;
        int v5 = (s.charAt(4) - 48) * 8;
        int v6 = (s.charAt(5) - 48) * 4;
        int v7 = (s.charAt(6) - 48) * 2;
        int v8 = (s.charAt(7) - 48);
        return v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8;

    }


    /**
     * 构造文件流，读取文件
     *
     * @param path 文件路径
     * @throws Exception
     */
    public void countTimes(String path) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(path);
        int read = fileInputStream.read();
        while (read != -1) {
            times[read]++;
            read = fileInputStream.read();
        }
        fileInputStream.close();
    }
}
