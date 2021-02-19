package Sorting;

//冒泡排序(比较相邻两个元素);一共进行n-1次循环,每一趟排序的次数在逐渐的减少
//两层循环,时间复杂度为O(n平方)

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 19:40
 * Description: No Description
 */
public class Bubble_Sorting {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};
        //第一趟,就是将最大的值排在最后
        int temp = 0;
        System.out.println(Arrays.toString(arr));
        System.out.println("================Bubble Sorting==================");
        for (int i = 0; i < arr.length - 1; i++) {
            //针对N个数,需要n-1次排序比较即可
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //每一次排序可以确定一个大值,同时可以让这个已经确定的大值退出排序操作
                //相邻的两个数中,将较大者向后移
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "次排序的结果:" + Arrays.toString(arr));
        }
        System.out.println("================Bubble Sorting==================");
        System.out.println(Arrays.toString(arr));
    }
}
