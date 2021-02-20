package Sorting;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 12:06
 * Description: No Description
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
//        quickSort(arr, 0, arr.length - 1);
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
        int l = left;
        int r = right;
        //pivot中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
