package cn.wpin.datastructure.tree.huffmzip;

/**
 * 测试文件压缩
 *
 * @author wpin
 */
public class Test {

    public static void main(String[] args) throws Exception {
        String srcPath = "D:\\1.txt";
        String destPath = "D:\\2.txt";
        String unzipDestPath = "D:\\3.txt";
        //创建压缩对象
        Compress compress = new Compress();
        //统计文件中0-255出现的次数
        compress.countTimes(srcPath);

        HuffmNode huffmNode = compress.createTree();

        compress.getHuffmCode(huffmNode, "");

        compress.compress(srcPath, destPath);

        Decompress decompress=new Decompress();

        decompress.decompress(destPath,unzipDestPath);
    }
}
