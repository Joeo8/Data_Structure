package Sorting;
//选择排序 每次遍历寻找最小值与当前值进行交换
//时间复杂度O(n^2)
//80000个随机数据  ==> 排序速度3秒左右 (冒泡排序耗时平均25秒)

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 21:27
 * Description: No Description
 */
public class Select_Sorting {
    public static void main(String[] args) {
        int[] arr = {101, 24, 119, 1};
        int [] arrs = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arrs[i] = (int) (Math.random()*80000);
        }
        long start = System.currentTimeMillis();
        SelectSorting(arrs);
        long end = System.currentTimeMillis();
        System.out.println("SelectSorting共耗时:"+(end-start)+"ms");
    }

    public static void SelectSorting(int[] arr) {
//        System.out.println(Arrays.toString(arr));
//        System.out.println("===============SelectSorting==================");
        for (int i = 0; i < arr.length - 1; i++) {
            //假定当前值就是最小值
            int minIndex = i;           //记录假冒的最小值的下标索引
            int min = arr[i];           //记录假冒的最小值的具体数值
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {    //在循环遍历过程中审核最小值
                    min = arr[j];
                    minIndex = j;
                }
            }
            //将最小值与初始设定的最小值进行交换(程序优化)
            if (minIndex != i) {      //假冒的最小值被证实,则进行交换操作
                arr[minIndex] = arr[i];     //将假冒的最小值放到后面
                arr[i] = min;               //将真正的最小值放在最前面
//                System.out.println("第" + (i + 1) + "次交换后的变化:" + Arrays.toString(arr));
            }
        }
        System.out.println("===============SelectSorting==================");
        System.out.println(Arrays.toString(arr));
    }
}
