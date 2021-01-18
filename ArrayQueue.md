## ArrayQueue(数组模拟队列)

<hr>

#### 队列介绍：

>+ 队列是一个有序列表，可用<font style="color:pink">数组</font>或<font style="color:pink">链表</font>进行实现.
>
>+ 遵循<font style="color:pink">先入先出</font>原则。即，先存入队列的数据先取出，后存入的数据要后取出。
>
>+ 示意图（使用数组模拟队列）：
>
>  ![](./Media/ArrayQueue.png)

<hr>

#### 数组模拟队列的思路：

>+ 队列本身是一个有序列表，如使用数组结构来存储队列的数据，则需要声明如下
>
>  ```java
>  //用数组模拟实现队列 -- 创建一个ArrayQueue类
>  class ArrayQueue {
>      private int maxSize;  //表示数组的最大容量
>      private int front;        //队列头
>      private int rear;          //队列尾
>      private int[] arr;        //该数组用于存放数据，模拟队列
>  ```
>
>+ 因为队列的输入、输出分别是从前、后端来处理，因此需要两个变量`front`及`rear`分别记录队列的前后端的下标。front会随着数据输出而变化，rear则是随着数据的输入而变化。
>
>  ```java
>  //添加数据到队列
>    public void addQueue(int n) {
>          //判断队列是否为满
>          if (isFull()) {
>              throw new RuntimeException("队列已满，无法加入新的数据");
>          }
>          arr[++rear] = n;       //rear后移一步，同时将新的数据添加进去
>      }
>  ```
>
>  ```java
>   //获取队列中的数据
>      public int getQueue() {
>          //判断队列是否为空
>          if (isEmpty()) {
>              throw new RuntimeException("队列为空，无法读取有效数据");
>          }
>          return arr[++front];    //front后移一步，同时获取对应位置的数据
>      }
>  ```

#### 代码实现：

```java
package Queue;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 15:50
 * Description: No Description
 */

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试数组模拟队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';   //用于接收用户输入的命令
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输入一个菜单
        while (loop) {
            System.out.println("================================");
            System.out.println("s --> show : 显示队列");
            System.out.println("e -->  exit : 退出系统");
            System.out.println("a -->   add : 添加数据到队列");
            System.out.println("g -->    get : 从队列中取出数据");
            System.out.println("h -->   head : 查看队列头数据");
            System.out.println("================================");
            key = scanner.next().charAt(0);              //接收用户输入命令
            switch (key) {
                case 's':                            //显示队列
                    try {
                        arrayQueue.showQueue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':                             //退出系统
                    scanner.close();
                    loop = false;
                    break;
                case 'a':                              //向队列添加数据
                    try {
                        System.out.println("请输入要添加的数据");
                        int num = scanner.nextInt();
                        arrayQueue.addQueue(num);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'g':                               //获取队列中的数据
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是\t%d \n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':                                 //查看队列的头数据
                    try {
                        int head = arrayQueue.headQueue();
                        System.out.printf("队列的头数据是\t%d\n",head);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
            System.out.println("队列退出！");
    }
}

//用数组模拟实现队列 -- 创建一个ArrayQueue类
class ArrayQueue {
    private int maxSize;  //表示数组的最大容量
    private int front;        //队列头
    private int rear;          //队列尾
    private int[] arr;        //该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int MaxSize) {
        this.maxSize = MaxSize;
        arr = new int[this.maxSize];
        front = -1;     //指向队列的头部，分析出front指向队列头部的前一个位置
        rear = -1;      //指向队列的尾部，分析出rear指向队列尾部的后一个位置
    }

    //判断队列为满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否为满
        if (isFull()) {
            throw new RuntimeException("队列已满，无法加入新的数据");
        }
        arr[++rear] = n;                   //rear后移一步，同时将新的数据添加进去
    }

    //获取队列中的数据
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法读取有效数据");
        }
        return arr[++front];            //front后移一步，同时获取对应位置的数据
    }

    //显示队列中的所有元素
    public void showQueue() {
        //遍历“数组”
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取到有效数据！");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d \n", i, arr[i]);
        }
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,没有headQueue !");
        }
        return arr[front + 1];
    }
}

```

#### Output：

```
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
s
java.lang.RuntimeException: 队列为空，无法获取到有效数据！
	at Queue.ArrayQueue.showQueue(ArrayQueueDemo.java:120)
	at Queue.ArrayQueueDemo.main(ArrayQueueDemo.java:31)
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
a
请输入要添加的数据
10
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
s
arr[0] = 10 
arr[1] = 0 
arr[2] = 0 
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
a
请输入要添加的数据
20
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
s
arr[0] = 10 
arr[1] = 20 
arr[2] = 0 
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
a
请输入要添加的数据
30
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
s
arr[0] = 10 
arr[1] = 20 
arr[2] = 30 
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
a
请输入要添加的数据
40
java.lang.RuntimeException: 队列已满，无法加入新的数据
	at Queue.ArrayQueue.addQueue(ArrayQueueDemo.java:102)
	at Queue.ArrayQueueDemo.main(ArrayQueueDemo.java:44)
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
h
取出的头数据是	10
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
s
arr[0] = 10 
arr[1] = 20 
arr[2] = 30 
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
h
取出的头数据是	10
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
g
取出的数据是	10 
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
h
取出的头数据是	20
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
g
取出的数据是	20 
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
h
取出的头数据是	30
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
s
arr[0] = 10 
arr[1] = 20 
arr[2] = 30 
================================
s --> show : 显示队列
e -->  exit : 退出系统
a -->   add : 添加数据到队列
g -->    get : 从队列中取出数据
h -->   head : 查看队列头数据
================================
e
队列退出！

Process finished with exit code 0

```



<hr>

#### 缺陷：

>+ 数据取出后，原本的存储位置不能循环利用。（即使把队列中的数据都取出也不能继续存放新的数据）
>+ getQueue()取出数据后，只要队列中数据不为空，showQueue()就能查看到所有的元素（部分已经取出）。

