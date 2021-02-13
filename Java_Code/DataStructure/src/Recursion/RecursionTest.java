package Recursion;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 13:24
 * Description: Recursion Test
 */
public class RecursionTest {
    public static void main(String[] args) {
        //通过打印问题,回顾递归调用机制
//        test(4);
        int res = factorial(4);
        System.out.println("结果为: " + res);
    }


    //打印问题
    public static void test(int n) {
        if (n >= 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    //阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }

}
