package cn.wpin.algorithm.other.map;


import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wangpin
 */
public class SyncMap {

    public static void main(String[] args) {
        TreeMap<String,String> treeMap=new TreeMap<>();
        treeMap= (TreeMap<String, String>) Collections.synchronizedMap(treeMap);
    }
}
