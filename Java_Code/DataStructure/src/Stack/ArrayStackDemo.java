package Stack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 21:34
 * Description: Stack (implement by Array)
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试数组实现的栈
        //创建一个ArrayStack表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner input = new Scanner(System.in);
        while (loop) {
            System.out.println("====================================");
            System.out.println("show:显示栈");
            System.out.println("exit:退出栈");
            System.out.println("push:加入栈");
            System.out.println("pop:取出栈");
            System.out.println("请输入您的指令 : ");
            key = input.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "exit":
                    input.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入有效数字:");
                    int value = input.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.println(pop);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("您输入的的指令有误！");
                    break;
            }
        }
        System.out.println("================ArrayStack==================");
    }
}

//定义一个ArrayStack栈
class ArrayStack {
    private int maxSize;    //表示栈的最大容量
    private int[] stack;  //用数组存放栈元素(数组实现其原理)
    private int top = -1;  //top表示栈顶,初始值赋 -1

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize]; //初始化数组，完成创建
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈操作--push
    public void push(int value) {
        //第一步--判断栈满
        if (isFull()) {
            System.out.println("栈满,无法加入有效数据!");
        } else {
            stack[++top] = value;
        }
    }

    //出栈操作--pop
    public int pop() {
        //第一步--判断栈空
        if (isEmpty()) {
            throw new RuntimeException("栈空！");
        } else {
            int value = stack[top];
            top--;
            return value;
        }
    }

    //显示栈中元素--list(从栈顶开始遍历)
    public void list() {
        //判断栈是否为空
        if (isEmpty()){
            System.out.println("栈为空,无有效数据!");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }

}