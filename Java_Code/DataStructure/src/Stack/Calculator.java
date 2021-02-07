package Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 22:56
 * Description: Calculator implements
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "30+2*6-2";      //中缀表达式
        //创建两个栈（一个数栈、一个运算符栈）
        ArrayStack1 numStack = new ArrayStack1(10);
        ArrayStack1 operStack = new ArrayStack1(10);
        //定义需要的相关变量
        int index = 0; //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//用于存放每次扫描的单个char字符
        String keepNum = ""; //用于数字拼接多位数
        //while循环扫描expression表达式
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是数字还是运算符
            if (operStack.isOper(ch)) {       //如果是运算符
                //判断当前运算符栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符,就进行比较;当前运算符优先级<=栈中操作符的优先级,
                    //则从数字栈中pop两个数,符号栈中pop一个符号进行运算,将得到结果push到数字栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //将运算结果放入数栈
                        numStack.push(res);
                        //操作完成后将当前的运算符放入符栈中去
                        operStack.push(ch);
                    } else {
                        //如果优先级高,则直接放入
                        operStack.push(ch);
                    }
                } else {
                    //如果符栈中为空,直接添加当前运算符
                    operStack.push(ch);
                }
            } else { //如果是数,则将字符1转换成数字1（ch-48）
                //bug：处理多位数(判断表达式中的下一位是否为数字,如果是则拼接)
                //处理多位数
                keepNum += ch;
                //如果ch已经是expression的最后一位,就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字,是则继续向后扫描,不是则入数栈
                    //这是只是向后看一下,index本身不变==》所以不能用index++
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符
                        numStack.push(Integer.parseInt(keepNum));
                        //此时注意将keepNum字符清空
                        keepNum = "";
                    }
                }
            }
            //让index自增,扫描表达式
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕,就顺序从数栈和符栈中pop,并执行
        while (true) {
            //如果符号栈为空,则数栈中只有一个数字即为结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        //将数栈中最后的数pop出
        System.out.printf("表达式结果为:%s = %d", expression, numStack.pop());
    }
}

//创建一个栈
class ArrayStack1 {
    private int maxSize;    //表示栈的最大容量
    private int[] stack;  //用数组存放栈元素(数组实现其原理)
    private int top = -1;  //top表示栈顶,初始值赋 -1

    //构造器
    public ArrayStack1(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize]; //初始化数组，完成创建
    }

    //增加一个方法,返回当前栈顶的值(只是查看并不弹出pop)
    public int peek() {
        return stack[top];
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
        if (isEmpty()) {
            System.out.println("栈为空,无有效数据!");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }


    //返回数字的运算符（用数字表示，数字越大优先级越大）
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; //假定目前简单运算中仅有这几种
        }
    }

    //判断是否为运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
//        return priority(val) != -1;
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; //存放计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}