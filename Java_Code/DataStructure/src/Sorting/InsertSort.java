package Sorting;
//插入排序
//先将第一个数当成一个有序列表,将剩余的其他数当成无序列表
//每次从无序列表中取一个数,在有序列表中寻找合适的位置加入其中
//80000数据测试平均耗时2100ms
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 22:05
 * Description: No Description
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 24, 119, 1};
        int [] arrs = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arrs[i] = (int) (Math.random()*80000);
        }
        long start = System.currentTimeMillis();
        insertSort(arrs);
        long end = System.currentTimeMillis();
        System.out.println("SelectSorting共耗时:"+(end-start)+"ms");
    }

    public static void insertSort(int[] arr) {
//        System.out.println(Arrays.toString(arr));
//        System.out.println("======================InsertionSorting===================");
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1; //即arr[1]前面的一个位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex != (i - 1)) {
                arr[insertIndex + 1] = insertVal;
//                System.out.println("第" + i + "轮插入后的顺序:" + Arrays.toString(arr));
            }
        }
        System.out.println("======================InsertionSorting===================");
        System.out.println(Arrays.toString(arr));
    }
}
