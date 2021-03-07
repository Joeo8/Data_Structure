package Search;

//改进二分查找（当符合条件的数值不唯一时,将所有的结果（index）放在数组中）

import java.util.ArrayList;
import java.util.List;

public class BinarySearch_ {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List resIndexList =  binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("ResIndexList:"+resIndexList);
    }

    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        int mid = (right + left) / 2;
        int midVal = arr[mid];

        //为避免StackOverFlow无限递归循环
        if (left > right) {
            return new ArrayList<>();
        }

        if (findVal > midVal) {             //向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {      //向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp -= 1; //将temp左移
            }
            resIndexList.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp += 1; //将temp左移
            }
           return resIndexList;
        }
      }
}
