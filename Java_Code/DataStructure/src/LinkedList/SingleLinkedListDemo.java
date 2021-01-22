package LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 14:23
 * Description: SingleLinkedList implementation
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试链表
        //第一步：创建节点
        HeroNode hero_1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero_2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero_3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero_4 = new HeroNode(4, "林冲", "豹子头");
        //第二步：创建一个SingleLinkedList
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        System.out.println("===============SingleLinkedList==============");
        singleLinkedList.show();
        //第三步：将node对象加入到SingleLinkedList中
        /*singleLinkedList.add(hero_1);
        singleLinkedList.add(hero_4);
        singleLinkedList.add(hero_2);
        singleLinkedList.add(hero_3);*/
        singleLinkedList.addByOrder(hero_1);
        singleLinkedList.addByOrder(hero_4);
        singleLinkedList.addByOrder(hero_3);
        singleLinkedList.addByOrder(hero_2);
        System.out.println("===================添加完成==================");
        singleLinkedList.show();
        singleLinkedList.addByOrder(hero_3);
//        singleLinkedList.show();
        //------------------------------链表反转--------------------------------------
        System.out.println("===================链表反转==================");
        singleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.show();
        //------------------------------------------------------------------------------
        singleLinkedList.del(4);
        singleLinkedList.del(1);
//        singleLinkedList.del(2);
//        singleLinkedList.del(3);
        System.out.println("===================删除完成==================");
        singleLinkedList.show();
        //-------------------------------------------------------------------------------
        HeroNode updateHero = new HeroNode(2, "俊逸", "麒麟");
        singleLinkedList.update(updateHero);
        System.out.println("===================修改完成==================");
        singleLinkedList.show();
        //-------------------------------------------------------------------------------
        System.out.println("===================查询完成==================");
        singleLinkedList.get(2);
        System.out.println("===============SingleLinkedList==============");


        //--------------------------------------面视题目测试------------------------------------
        //统计单链表中 有效节点的个数（length）
        int length = singleLinkedList.getLength(singleLinkedList.getHead());
        System.out.println("有效结点的个数为：" + length);
        //查找单链表中倒数第k个节点（循环遍历length-k移动指针）
        HeroNode res = singleLinkedList.getLastIndex(singleLinkedList.getHead(), 1);
        System.out.println("res= " + res);

    }

}

//创建单链表SingleLinkedList来管理node对象
class SingleLinkedList {
    //首先：初始化一个头节点，保证头节点不动（只是标记作用，不存放具体数据）
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

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
            if (temp.next == null) {                //说明已经到了单链表的最后一个位置
                break;
            }
            if (temp.next.no > heroNode.no) {                       //此时位置正合适
                break;
            } else if (temp.next.no == heroNode.no) {           //编号重复，做好记录
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

    //删除节点
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {      //已经到了链表的最后
                break;
            }
            if (temp.next.no == no) {    //删除时机
                flag = !flag;
                break;
            }
            temp = temp.next;
        }
        //出了while只有两种情况：找到该节点，或者该节点不存在
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("不存在该节点！");
        }
    }

    //修改节点信息（根据no编号进行修改，即no不可更改）
    public void update(HeroNode heroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //根据no编号索引需要修改的节点数据
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break; //表示已经遍历完链表
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            //没有找到要修改的节点
            System.out.println("没要找到节点信息，无法进行修改！");
        }

    }

    //查询对应节点信息
    public void get(int no) {
        //判断链表是否未空
        if (head.next == null) {
            System.out.println("链表为空！");
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = !flag;
//                System.out.println(temp);
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println(temp);
        } else {
            System.out.println("不存在该节点！");
        }
    }

    //显示链表中的各个节点【遍历】
    public void show() {
        //判断链表是否未空
        if (head.next == null) {
            System.out.println("链表为空！");
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


    //------------------------------------面试题----------------------------------------
    //获取链表中有效节点的个数（带头结点的链表不统计头节点）
    public static int getLength(HeroNode head) {
        //判断链表是否为空
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表中倒数第k个结点
    public static HeroNode getLastIndex(HeroNode head, int k) {
        //判断链表是否为空
        if (head.next == null) {
            return null;
        }
        //获取链表的长度
        int length = getLength(head);
        //边界判断
        if (k <= 0 || k > length) {  //相当于越界，查找不到对应的节点
            return null;
        }
        //定义辅助节点，开始遍历
        HeroNode cur = head.next;
        for (int i = 0; i < length - k; i++) {        //定位到倒数第k个节点
            cur = cur.next;
        }
        return cur;
    }

    //单链表的反转 （有点难度） --腾讯
    public static void reverseList(HeroNode head) {
        //判断链表是否为空，或者只有一个节点（反转无意义）
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助变量来辅助遍历链表
        HeroNode cur = head.next;
        HeroNode next = null;          //指向当前节点【cur】的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历链表时，每遍历一个节点就将其取出，并放在新的链表的最前端
        while (cur != null) {
            next = cur.next;  //保存后一个节点
            cur.next = reverseHead.next; //将cur节点的下一个节点指向新链表的最前端
            reverseHead.next = cur;
            cur = next;  //将当前节点后移
        }
        head.next = reverseHead.next; //将原始链表指向新链表的头元素
    }
}


//定义HeroNode，每一个HeroNode就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;                       //指向下一个节点

    //创建构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示效果，重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}