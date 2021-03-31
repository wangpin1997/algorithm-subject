package cn.wpin.datastructure.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树
 *
 * @author wpin
 */
public class TrieTree {


    private TrieTreeNode root;

    private TrieTree() {
        root = new TrieTreeNode();
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"code", "demo", "cod", "coding"};
        TrieTree trieTree = new TrieTree();
        for (String string : strings) {
            trieTree.insert(string);
        }
        System.out.println(trieTree.root);
        System.out.println(trieTree.search("demo"));
        System.out.println(trieTree.search("dem"));
        System.out.println(trieTree.startWith("demoq"));
        System.out.println(trieTree.startWith("demo"));
    }

    /**
     * 前缀树添加节点
     *
     * @param word 插入的关键字
     */
    private void insert(String word) {
        TrieTreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieTreeNode());
            }
            node = node.children.get(c);
        }
        node.isTrie = true;
    }

    /**
     * 搜索前缀树中是否包含字符串
     *
     * @param word 关键字字符串
     * @return boolean
     */
    private boolean search(String word) {
        TrieTreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
        }
        return node.isTrie;
    }

    /**
     * 判断前缀树是否有以prefix开头的
     *
     * @param prefix 前缀
     * @return boolean
     */
    private boolean startWith(String prefix) {
        TrieTreeNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            Character c = prefix.charAt(i);
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
        }
        return true;

    }


    private class TrieTreeNode {
        Boolean isTrie;
        Map<Character, TrieTreeNode> children;

        TrieTreeNode() {
            isTrie = true;
            children = new HashMap<>(16);
        }
    }
}
