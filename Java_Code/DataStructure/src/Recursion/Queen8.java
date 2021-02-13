package Recursion;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 23:44
 * Description: Queen 8
 */
public class Queen8 {
    int max = 8;                            //定义共有多少个皇后
    int[] array = new int[max];             //用一维数组存放皇后的步法
    static int count = 0;                   //统计解法个数
    static int judgeCount = 0;              //统计共判断多少次

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();       //实例化Queen
        queen8.check(0);                 //递归回溯求解八皇后
        System.out.printf("一共有%d种解法", count);
        System.out.println();
        System.out.printf("一共有%d次判断", judgeCount);
    }

    //编写一个方法,放置第n个皇后
    //细节:check在每次递归中都有回溯
    private void check(int n) {
        if (n == max) {          //如果n为8,则表示已经该放置第9个皇后,此时已经完成八皇后问题,直接输出即可
            print();
            return;
        }
        //依次放入皇后,并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先将当前皇后放置在第一列
            array[n] = i;
            //判断当放置第n个皇后到第i列时是否冲突
            if (judge(n)) {      //true表示不冲突
                check(n + 1);  //递归放置下一个皇后
            }
        }
    }

    //编写一个方法判断是否冲突
    private boolean judge(int n) {
        judgeCount++;
        //将当前皇后逐个对应其他皇后检查冲突
        for (int i = 0; i < n; i++) {
            //判断是否在同一列,或在同一斜线(斜率);一维数组的下标对应行号,所以行号自增,不会存在冲突
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //编写一个输出方法,
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
