package Sorting;

//冒泡排序(比较相邻两个元素);一共进行n-1次循环,每一趟排序的次数在逐渐的减少
//两层循环,时间复杂度为O(n^2)

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 19:40
 * Description: No Description
 */
public class Bubble_Sorting {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, -2};
//        int[] arr = {3, 9, 10, 15, 20};
        int[] arrs = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arrs[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        bubbleSort(arrs);
        long end = System.currentTimeMillis();
        System.out.println("排序共耗时：" + (end - start) + "ms");
    }

    public static void bubbleSort(int[] arr) {
        //第一趟,就是将最大的值排在最后
        int temp = 0;
        boolean flag = false;  //标识变量,是否进行了交换操作(程序优化)
        System.out.println(Arrays.toString(arr));
        System.out.println("================Bubble Sorting==================");
        for (int i = 0; i < arr.length - 1; i++) {
            //针对N个数,需要n-1次排序比较即可
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //每一次排序可以确定一个大值,同时可以让这个已经确定的大值退出排序操作
                //相邻的两个数中,将较大者向后移
                if (arr[j] > arr[j + 1]) {
                    flag = true;                //出现交换操作,将flag标识改位true
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {  //如果在一次排序比较中,没有对任何数据进行交换操作(可视为已经有序)
                break;
            } else {
                flag = false;                   //重置flag,用于下一次判断
            }
//            System.out.println("第" + (i + 1) + "次排序的结果:" + Arrays.toString(arr));
        }
        System.out.println("================Bubble Sorting==================");
        System.out.println(Arrays.toString(arr));
    }
}
