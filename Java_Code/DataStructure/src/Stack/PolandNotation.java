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

        //完成将一个中缀表达式转换成后缀表达式
        /*
         *思路:
         *   1）将String类型的中缀表达式转换成对应的List
         *   2）将得到的中缀表达式List转换成后悔表达式List
         */
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);  //中缀表达式
        System.out.println(list);
        List<String> rpnExpression = parseSuffixExpression(list); //后缀表达式
        System.out.println(rpnExpression);
        int res = calculate(rpnExpression);
        System.out.println("计算结果为: "+res);
        /*//先定义一个逆波兰表达式
        //(3+4)*5-6 ==> 3 4 + 5 * 6 -
        //为了方便查看,逆波兰表达式中,数字和符合用空格隔开
        String suffixExpression = "3 4 + 5 * 6 - ";
        *//* 思路:
         *   1.先将“3 4 + 5 * 6 - ”放到ArrayList中
         *   2.将ArrayList作为参数传递给方法,在遍历的过程中集合stack完成计算
         *//*
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        int res = calculate(rpnList);
        System.out.println(suffixExpression + "逆波兰计算结果为 ==》" + res);*/
    }

    //将中缀表达式由String变成List,方便操作
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List存放中缀表达式中的内容
        List<String> ls = new ArrayList<>();
        int i = 0;     //指针,用于遍历中缀表达式
        String str;    //对多位数进行拼接
        char c;        //遍历到字符时,加入到c中
        do {
            //如果c不是一个数字,则加入到ls中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {      //如果是数字,则需要考虑多位数的情况(拼接)
                str = ""; //将str置空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c; //拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //编写核心代码,实现中缀表达式转后缀表达式
    public static List<String> parseSuffixExpression(List<String> ls) {
        //定义两个栈,一个存放数字,一个存放操作符
        Stack<String> s1 = new Stack<>();//符号栈
        //说明:在数栈中,不需要pop操作,而且最终需要将结果逆序输出,所以这里直接使用List更合适些
        List<String> s2 = new ArrayList<>();
        //遍历ls
        for (String item :
                ls) {
            //如果是数,直接加入到s2中(正则表达式匹配)
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);  //如果是左括号直接加入s1中
            } else if (item.equals(")")) {
                //如果是右括号,则依次弹出s1栈顶的运算符,并压入s2,直到遇到左括号为止,此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();  //将左括号一并弹出(销毁处理)
            } else {
                //判断优先级
                //当item优先级小于等于s1栈顶的运算符,将s1栈顶的运算符弹出并压入s2中,反复执行此过程
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //最后还需要将item运算符押入到s1中
                s1.push(item);
            }
        }
        //将s1中的剩余运算符依次加入到s2中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; //因为s2用的是List结构,输出的结果即为所求
    }

    //写一个方法单个取出suffixExpression表达式中的元素放入List中,方便操作
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

    //实现后缀表达式计算
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
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//编写一个Operation类,用于返回代表运算符的优先级的数字
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 1;
    private static int DIV = 1;

    //写一个方法,通过运算符拿到优先级数字
    public static int getValue(String oper) {
        int result = 0;
        switch (oper) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}