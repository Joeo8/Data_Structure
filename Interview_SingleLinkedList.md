[TOC]



## 单链表的面试题型

<hr>

#### 一. 获取链表中有效节点的个数

>##### 思路：
>
>+ 遍历链表的同时，记录长度`length ++`

>```java
>//获取链表中有效节点的个数（带头结点的链表不统计头节点）
>    public static int getLength(HeroNode head) {
>        //判断链表是否为空
>        if (head.next == null) {
>            return 0;
>        }
>        int length = 0;
>        HeroNode cur = head.next;
>        while (cur != null) {
>            length++;
>            cur = cur.next;
>        }
>        return length;
>    }
>```

#### 二. 查找倒数第K个节点的内容 -- 新浪

>##### 思路：
>
>+ 先获取链表的长度
>
>+ for循环遍历（`length-K`）次，指针移动到目标节点，打印输出

>```java
> //查找单链表中倒数第k个结点
>    public static HeroNode getLastIndex(HeroNode head, int k) {
>        //判断链表是否为空
>        if (head.next == null) {
>            return null;
>        }
>        //获取链表的长度
>        int length = getLength(head);
>        //边界判断
>        if (k <= 0 || k > length) {  //相当于越界，查找不到对应的节点
>            return null;
>        }
>        //定义辅助节点，开始遍历
>        HeroNode cur = head.next;
>        for (int i = 0; i < length - k; i++) {    //定位到倒数第k个节点
>            cur = cur.next;
>        }
>        return cur;
>    }
>```

#### 三. 单链表的反转（有点难度） -- 腾讯

>##### 思路：
>
>+ 新建一个HeadNode，遍历原有链表的时候，将当前指向的节点添加到新建HeadNode节点的第一个位置 (形象理解在HeadNode中`后加入的节点将先加入的节点向后挤`)
>
>+ 最后将原有链表的头节点指向新建头结点的下一个位置
>
>  ```java
>  //核心思路:
>  while(cur != null){
>      nextNode = cur.next; 
>      cur.next = ReverseHead.next;
>      ReverseHead.next = cur;
>      cur = next ;
>  }
>  head.next = ReverseHead.next;
>  ```

>```java
>//单链表的反转 （有点难度） --腾讯
>    public static void reverseList(HeroNode head) {
>        //判断链表是否为空，或者只有一个节点（反转无意义）
>        if (head.next == null || head.next.next == null) {
>            return;
>        }
>        //定义一个辅助变量来辅助遍历链表
>        HeroNode cur = head.next;
>        HeroNode next = null;          //指向当前节点【cur】的下一个节点
>        HeroNode reverseHead = new HeroNode(0, "", "");
>        //遍历链表时，每遍历一个节点就将其取出，并放在新的链表的最前端
>        while (cur != null) {
>            next = cur.next;  //保存后一个节点
>            cur.next = reverseHead.next; //将cur节点的下一个节点指向新链表的最前端
>            reverseHead.next = cur;
>            cur = next;  //将当前节点后移
>        }
>        head.next = reverseHead.next; //将原始链表指向新链表的头元素
>    }
>```

#### 四. 逆序打印输出链表中各节点 -- 百度

>##### 思路：
>
>+ 第一种方法，将链表反转，然后打印输出（破坏了原有的结构）:small_red_triangle:
>+ 第二种方法，压栈`push`出栈`pop`    `Stack`

>```java
>  //将链表逆序打印输出[栈]
>    public static  void reversePrint(HeroNode head){
>        //判断链表是否为空
>        if (head.next == null){
>            return ;  //空链表，打印无意义
>        }
>        //创建一个栈，依次将链表中的元素压入栈中，然后从栈顶pop
>        Stack<HeroNode> stack = new Stack<>();
>        HeroNode cur = head.next;
>        while(cur != null){
>            stack.push(cur);
>            cur = cur.next;  //cur节点后移
>        }
>        //将栈中数据打印输出
>        while(stack.size()>0){
>            System.out.println(stack.pop());
>        }
>    }
>```

