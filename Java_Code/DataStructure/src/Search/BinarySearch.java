package Search;
//二分查找 ==> 必须是有序数组 （现将数组变成有序数组）

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = binarySearch(arr,0,arr.length-1,102);
        System.out.printf("目标数值对应下标为：%d",index);








    }

    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        int mid = (right + left) / 2;
        int midVal = arr[mid];

        //为避免StackOverFlow无限递归循环
        if (left > right){
            return -1;
        }

        if (findVal > midVal) {             //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {      //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
