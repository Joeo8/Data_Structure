## SingleLinkedList（单链表）

<hr>

#### 单链表：

>+ 单链表结构中，将数据存放在各个节点中。如下图所示，在每个节点内部，有两部组成。（data+next）即数据和指针。所谓单链表无疑是链式存储方式，节点中的next指针指向下一个节点，存储空间不是连续的。
>
>+ 单链表结构中有两种形式
>
>  + 带头结点的单链表
>
>    头节点起标记作用，节点中不存放具体数据。next指向第一个有具体数据的节点
>
>  + 不带头结点的单链表
>
>![SingleLinkedList](./Media/SingleLinkedList_01.jpg)



<hr>

#### 核心代码：

```java
//创建单链表SingleLinkedList来管理node对象
class SingleLinkedList {
    //首先：初始化一个头节点，保证头节点不动（只是标记作用，不存放具体数据）
    private HeroNode head = new HeroNode(0, "", "");

    //添加节点到链表中  (当不考虑编号时，找到最后一个节点，然后将next指向新增的节点)
    public void add(HeroNode heroNode) {
        //head头节点不能动，此时用一个辅助变量temp记录头节点
        HeroNode temp = head;
        //遍历链表，找到链表的最后一个节点
        while (true) {
            if (temp.next == null) {
                break;                         //此时说明已经到链表的最后一个
            }
            //如果没有到最后一个节点，将temp指向后一个节点
            temp = temp.next;
        }
        //退出while循环之后，此时可以保证temp指向最后一个节点
        temp.next = heroNode;         //将最后一个节点指向新的节点，完成添加
    }

    //添加方法升级(按编号顺序添加)
    public void addByOrder(HeroNode heroNode) {
        //定义一个标识记录特殊情况（编号重复不能插入）
        boolean flag = false;
        //定义temp辅助变量拿到head
        HeroNode temp = head;
        //遍历
        while (true) {
            if (temp.next == null) {       //说明已经到了单链表的最后一个位置
                break;
            }
            if (temp.next.no > heroNode.no) {         //此时位置正合适
                break;
            } else if (temp.next.no == heroNode.no) { //编号重复，做好记录
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //出了while循环就要对flag进行判断
        if (flag) {              //当flag为true表明编号已存在，不可添加
            System.out.println("该编号已存在 , 不可重复添加！");
        } else {
            //此时，让新节点指向原本temp的下一个节点，并让temp指向新的节点
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点信息（根据no编号进行修改，即no不可更改）
    public void update(HeroNode heroNode){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空！");
            return ;
        }
        //根据no编号索引需要修改的节点数据
        HeroNode temp = head.next ;
        boolean flag = false ;
        while(true){
            if (temp == null){
                break ; //表示已经遍历完链表
            }
            if(temp.no == heroNode.no){
                flag = true ;
                break ;
            }
            temp = temp.next ;
        }
        //根据flag判断是否找到要修改的节点
        if (flag){
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }else{
            //没有找到要修改的节点
            System.out.println("没要找到节点信息，无法进行修改！");
        }

    }

    //显示链表中的各个节点【遍历】
    public void show() {
        //判断链表是否未空
        if (head.next == null) {
            throw new RuntimeException("链表为空！");
        }

        //因为头节点不能懂，所以 new 一个赋值变量进行遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表末尾
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将当前指向位置后移一个 (不然会导致死循环)
            temp = temp.next;
        }

    }
}

```

