package cn.wpin.algorithm.other;

import com.google.common.collect.Lists;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏数组实现，作用，节省资源
 *
 * @author wangpin
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建原始数组（棋盘）
        int[][] arr1 = new int[11][11];
        arr1[1][2] = 1;
        arr1[2][3] = 2;
        //打印下原数组
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                System.out.printf("%d\t", arr1[i][j]);
            }
            System.out.println();
        }
        //统计棋子（非零）个数
        int num = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                if (arr1[i][j] != 0) {
                    num++;
                }
            }
            System.out.println();
        }
        //创建稀疏数组
        int[][] sparseArray = new int[num + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = num;

        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                if (arr1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = arr1[i][j];
                }
            }
        }
        //打印出稀疏数组
        for (int i = 0; i < sparseArray.length; i++) {
            for (int j = 0; j < sparseArray[i].length; j++) {
                System.out.printf("%d\t", sparseArray[i][j]);
            }
            System.out.println();
        }
        int [][] printArr=new int[3][3];
        try {
            //写入磁盘
            write("G:\\1.txt",sparseArray);
            //磁盘中读取
            read("G:\\1.txt",printArr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //稀疏数组再转换成原数组
        int[][] arr2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i <=sparseArray.length-1; i++) {
            arr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //打印还原后的数组
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[i].length; j++) {
                System.out.printf("%d\t", arr2[i][j]);
            }
            System.out.println();
        }

    }

    private static void write(String path,int [][] arr) throws IOException {
        //存放数组数据的文件
        File file = new File(path);
        //文件写入流
        FileWriter out = new FileWriter(file);
        //将数组中的数据写入到文件中。每行各数据之间TAB间隔
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                out.write(arr[i][j]+"\t");
            }
            out.write("\r\n");
        }
        out.close();
    }

    private static void read(String path,int [][] arr) throws IOException {
        //读取存放数组数据的文件
        File file = new File(path);
        //文件读入
        FileReader in = new FileReader(file);
        BufferedReader bufferedReader=new BufferedReader(in);
        List<String[]> list=new ArrayList<>();
        String[] arrStr;
        String x;
        while ((x=bufferedReader.readLine())!=null){
            arrStr=x.split("\t");
            list.add(arrStr);
        }
        //将数组中的数据写入到文件中。每行各数据之间TAB间隔
        for(int i=0;i<list.size();i++){
            for (int j = 0; j <list.get(i).length ; j++) {
                arr[i][j]=Integer.valueOf(list.get(i)[j]);
            }
        }
    }
}
