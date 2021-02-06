package LinkedList;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 20:28
 * Description: Stack
 */
public class StackTest {
    //显示栈Stack的基本使用
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("张三");
        stack.add("李四");
        stack.add("王五");
        //出栈
        while(stack.size() > 0){
            //pop将栈顶的数据取出
            System.out.println(stack.pop());
        }
    }
}
