package Sorting;
//快速排序
//80000数据进行测试耗时60ms
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 12:06
 * Description: No Description
 */
public class QuickSort {
    public static void main(String[] args) {
        /*int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));*/
        int[] arrs = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arrs[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        quickSort(arrs, 0, arrs.length - 1);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(arrs));
        System.out.println("=====================QuickSort=======================");
        System.out.println("QuickSorting共耗时:" + (end - start) + "ms");
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;   //左下标
        int r = right;  //右下标
        //pivot中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0;   //临时变量,用于交换操作
        //while循环中,将比pivot大的放右边,小的放左边
        while (l < r) {
            //左下标右移,寻找目标值
            while (arr[l] < pivot) {
                l++;
            }
            //右下标左移
            while (arr[r] > pivot) {
                r--;
            }
            //如果左右指针相遇,说明当前轮次已经排序完毕
            if (l >= r) {
                break;
            }
            //找到目标值后进行交换操作
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //左右指针指向的值是pivot,则反向改变下标(避免与pivot无限循环交换)
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }
        //如果l和r相遇,则相应移动下标值(否则会出现栈溢出异常)
        if (l == r) {
            l++;
            r--;
        }
        //左右递归排序
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
