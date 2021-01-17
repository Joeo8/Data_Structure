
/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 16:03
 * Description: No Description
 */
//  ====================SparseArray实现===========================
public class SparseArray {
    //创建一个原始的二维数组 11*11
    //0 表示没有棋子  1 表示黑棋 2 表示蓝棋
    public static void main(String[] args) {
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[2][5] = 1;
        System.out.println("原始棋盘（二维数组）如下：");
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println("===============================================");
        /*=========================SparseArray===========================*/
        //将二维数组转化为稀疏数组
        //1.循环遍历二维数组得到二维数组中的有效值的个数
        int sum = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("原始二维数组中有效数据有 " + sum + " 个");
        //2.创建对应的稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        //3.给稀疏数组赋值
        sparseArray[0][0] = chessArray.length;
        sparseArray[0][1] = chessArray[0].length;
        sparseArray[0][2] = sum;
        //遍历原始二维数组，将非0元素存放到稀疏数组中
        int count = 0;       //count 用于记录时对应的第几个非零数据
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    count++;     //需要一个自增的字数器来向稀疏数组中添加非零数据
                    sparseArray[count][0] = i;                              //行
                    sparseArray[count][1] = j;                              //列
                    sparseArray[count][2] = chessArray[i][j];     //值
                }
            }
        }
        //4.输出稀疏数组
        System.out.println("稀疏数组如下：");
        System.out.println("row\tcol\tval\t");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",
                    sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        System.out.println("===================================================\n" +
                "将稀疏数组恢复为原始数组如下");
        //=========稀疏数组转换为原始二维数组========== ===
        /*
            1.先读取稀疏数组的第一行，根据稀疏数组的第一行数据创建对应的二维数组
            2.再读取稀疏数组的后几行数据，并赋值给原始的二维数组
        */
        int chessArray2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //输出恢复的二维数组
        for (int[] row : chessArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }
}
