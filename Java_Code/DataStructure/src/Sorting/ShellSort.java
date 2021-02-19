package Sorting;

///希尔排序(缩小增量排序)
//将原始数组细分为多个组,在各个组中插入排序,并一次合并大小组,直至剩下一个组(即整个原始数组)
//交换式80000数据测试耗时10s
//移位式80000数据测试耗时1s

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 23:06
 * Description: No Description
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arrs = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arrs[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
//        shellSort(arrs);
        shellSort2(arrs);
        long end = System.currentTimeMillis();
        System.out.println("SelectSorting共耗时:" + (end - start) + "ms");
    }

    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        //增量gap,并逐步缩小增量(缩小增量排序)
        //将原始数组细分为多个小组,gap代表步长,也代表组数(增量)
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //针对组内的数值进行插入排序
            for (int i = gap; i < arr.length; i++) {
                //将组内的数据利用步长进行排序(交换式,可以当作是冒泡)
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("希尔排序第" + (++count) + "轮：" + Arrays.toString(arr));
        }
        System.out.println("========================ShellSort============================");
        System.out.println(Arrays.toString(arr));
    }

    //移步式希尔排序(高效！！！)
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素,逐个对其所在的组进行直接插入排序(移动式)
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
        System.out.println("========================ShellSort============================");
        System.out.println(Arrays.toString(arr));

    }
}
