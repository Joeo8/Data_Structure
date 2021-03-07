package Search;

//线性查找
public class SeqSearch {
    public static void main(String[] args) {
        // 无需数组
        int[] arr = {1, 9, 11, -1, 34, 89};
         int index = seqSearch(arr,11);
         if (index == -1){
             System.out.println("不存在目标数字");
         }else{
             System.out.printf("找到目标值，对应下表为：%d",index);
         }
    }
    // 较为简单的实现线性查找（找到一个目标值就返回）
    public static int seqSearch(int[] arr, int value) {
        //现线性查找是逐一比对，发现有相同时就返回其下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i ;
            }
        }
        return  -1;
    }
}
