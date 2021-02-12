package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 16:21
 * Description: PolandNotation Implement
 */
public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        //(3+4)*5-6 ==> 3 4 + 5 * 6 -
        //为了方便查看,逆波兰表达式中,数字和符合用空格隔开
        String suffixExpression = "3 4 + 5 * 6 - ";
        /*思路:
         *   1.先将“3 4 + 5 * 6 - ”放到ArrayList中
         *   2.将ArrayList作为参数传递给方法,在遍历的过程中集合stack完成计算
         */
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        int res = calculate(rpnList);
        System.out.println(suffixExpression+"逆波兰计算结果为 ==》"+res);
    }

    //写一个方法单个取出suffixExpression表达式中的元素
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression按设定的空格分隔
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele :
                split) {
            list.add(ele);
        }
        return list;
    }

    /*
     * 完成对逆波兰表达式的运算
     *  1）从左至右扫描,将3和4压入堆栈中
     *  2）遇到 + 运算符,此时弹出3和4,计算和并再次压入堆栈中
     *  3）将5压入栈中
     *  4）接下里是 * 运算,弹出5和7,计算结果并压入栈中
     *  5）将6压入栈中
     *  6）最后是 - 运算符,计算35-6的值,此时即为最终结果
     * */

    //实现后缀表达式计算操作
    public static int calculate(List<String> ls) {
        //创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item :
                ls) {
            //使用正则表达式进行匹配
            if (item.matches("\\d+")) {   //配到到数字,则直接压栈中
                stack.push(item);
            } else { //匹配到运算符,pop弹出两个数进行计算再次压栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;    //后缀表达式中,栈底的元素减去栈顶的元素
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("操作符有误！");
                }
                stack.push(""+res );
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
