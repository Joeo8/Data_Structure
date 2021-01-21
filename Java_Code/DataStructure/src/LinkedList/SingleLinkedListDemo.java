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
        //第三步：将node对象加入到SingleLinkedList中
        singleLinkedList.add(hero_1);
        singleLinkedList.add(hero_4);
        singleLinkedList.add(hero_2);
        singleLinkedList.add(hero_3);

        //第四步：显示SingleLinkedList中的各个node
        singleLinkedList.show();
    }

}

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