package cn.wpin.datastructure.tree.huffmzip;

import lombok.Data;

@Data
public class HuffmNode {

    /**
     * 数据域
     */
    private int data;
    /**
     * 索引
     */
    private int index;
    /**
     * 左子节点
     */
    private HuffmNode left;
    /**
     * 右子节点
     */
    private HuffmNode right;

    /**
     * 哈夫曼节点的构造函数
     *
     * @param data
     * @param index
     */
    public HuffmNode(int data, int index) {
        this.data = data;
        this.index = index;
    }


}
