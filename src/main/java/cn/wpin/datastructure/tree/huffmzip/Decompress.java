package cn.wpin.datastructure.tree.huffmzip;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 哈夫曼编码解压缩类
 *
 * @author wpin
 */
public class Decompress {
    /**
     * 每个编码的长度
     */
    private int[] codeLengths = new int[256];
    /**
     * 对应的哈夫曼编码值
     */
    private String[] codeMap = new String[256];

    public static void main(String[] args) {

        Decompress d = new Decompress();
        d.decompress("C:\\Users\\Administrator\\Desktop\\my.docx.zip",
                "C:\\Users\\Administrator\\Desktop\\mydecompress.docx");

    }


    public void decompress(String srcpath, String destpath) {

        try {
            FileInputStream fis = new FileInputStream(srcpath);
            FileOutputStream fos = new FileOutputStream(destpath);
            int value;
            int codeLength = 0;
            String code = "";
            //还原码表
            for (int i = 0; i < codeLengths.length; i++) {
                value = fis.read();
                codeLengths[i] = value;
//				System.out.println(times[i]);
                codeLength += codeLengths[i];
            }

            //得到总长度
            //将总长度除以8的到字节个数
            int len = codeLength / 8;
            //如果不是8的倍数，则字节个数加1（对应压缩补0的情况）
            if ((codeLength) % 8 != 0) {
                len++;
            }
            //读取哈夫曼编码
//			System.out.println("codeLength:"+len);
            for (int i = 0; i < len; i++) {
                //把读到的整数转换成二进制
                code += changeIntToString(fis.read());

            }
//			System.out.println("哈夫曼编码："+code);

            for (int i = 0; i < codeMap.length; i++) {
                //如果第i个位置不为0 ，则说明第i个位置存储有哈夫曼编码
                if (codeLengths[i] != 0) {
                    //将得到的一串哈夫曼编码按照长度分割分割
                    String ss = code.substring(0, codeLengths[i]);
                    codeMap[i] = ss;
                    code = code.substring(codeLengths[i]);
                } else {
                    //为0则没有对应的哈夫曼编码
                    codeMap[i] = "";
                }
            }

            //读取压缩的文件内容
            StringBuilder codeContent = new StringBuilder();
            while (fis.available() > 1) {
                codeContent.append(changeIntToString(fis.read()));
            }
            //读取最后一个
            value = fis.read();
            //把最后补的0给去掉
            codeContent = new StringBuilder(codeContent.substring(0, codeContent.length() - value));

            for (int i = 0; i < codeContent.length(); i++) {

                String codecontent = codeContent.substring(0, i + 1);

                for (int j = 0; j < codeMap.length; j++) {
                    if (codeMap[j].equals(codecontent)) {
//						System.out.println("截取的字符串："+codecontent);
                        fos.write(j);
                        fos.flush();
                        codeContent = new StringBuilder(codeContent.substring(i + 1));
//						System.out.println("截取后剩余编码长度："+codeContent.length());
//						count=1;
                        i = -1;
                        break;
                    }
                }
            }
//			}

            fos.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 十进制转二进制字符串
     *
     * @param value
     * @return
     */
    private String changeIntToString(int value) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            s.insert(0, value % 2);
            value = value / 2;
        }
        return s.toString();
    }

}
