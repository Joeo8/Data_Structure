package LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 11:48
 * Description: DoubleLinkedList implementation
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试链表
        //第一步：创建节点
        HeroNode1 hero_1 = new HeroNode1(1, "宋江", "及时雨");
        HeroNode1 hero_2 = new HeroNode1(2, "卢俊义", "玉麒麟");
        HeroNode1 hero_3 = new HeroNode1(3, "吴用", "智多星");
        HeroNode1 hero_4 = new HeroNode1(4, "林冲", "豹子头");
        //第二步：创建一个SingleLinkedList
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        System.out.println("===============DoubleLinkedList==============");
        //向链表中添加数据
        doubleLinkedList.addAA(hero_1);
        doubleLinkedList.addAA(hero_3);
        doubleLinkedList.addAA(hero_2);
        doubleLinkedList.addAA(hero_4);
        System.out.println("===================添加完成==================");
        doubleLinkedList.list();
        //删除链表中的节点数据
        doubleLinkedList.del(3);
        System.out.println("===================删除完成==================");
        doubleLinkedList.list();
        //修改链表中的节点数据
        HeroNode1 updateHero = new HeroNode1(2, "俊逸", "麒麟");
        doubleLinkedList.update(updateHero);
        System.out.println("===================修改完成==================");
        doubleLinkedList.list();
        //查询对应节点信息
        System.out.println("===================查询完成==================");
        doubleLinkedList.get(1);
        System.out.println("===============DoubleLinkedList==============");
    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    //首先：初始化一个头节点，保证头节点不动（只是标记作用，不存放具体数据）
    private HeroNode1 head = new HeroNode1(0, "", "");

    //返回头节点
    public HeroNode1 getHead() {
        return head;
    }

    //遍历双向链表
    public void list() {
        //判断链表是否未空
        if (head.next == null) {
            System.out.println("链表为空！");
        }
        //因为头节点不能懂，所以 new 一个赋值变量进行遍历
        HeroNode1 temp = head.next;
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

    //添加节点到链表中 (默认实现在尾部添加新节点)
    public void add(HeroNode1 heroNode) {
        //head头节点不能动，此时用一个辅助变量temp记录头节点
        HeroNode1 temp = head;
        //遍历链表，找到链表的最后一个节点
        while (true) {
            if (temp.next == null) {
                break;                         //此时说明已经到链表的最后一个
            }
            //如果没有到最后一个节点，将temp指向后一个节点
            temp = temp.next;
        }
        //退出while循环之后，此时可以保证temp指向最后一个节点
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //添加方法升级(按编号顺序添加)
    public void addAA(HeroNode1 heroNode) {
        //定义一个标识记录特殊情况（编号重复不能插入）
        boolean flag = false;
        //定义temp辅助变量拿到head
        HeroNode1 temp = head;
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
            //先指向temp的下一个，保证链表不会断的情况下，完成pre的对接
            heroNode.next = temp.next;
            heroNode.pre = temp;
            temp.next = heroNode;
            temp.next.pre = heroNode;
        }
    }


    //修改节点的内容   (和单链表操作相同)
    public void update(HeroNode1 heroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //根据no编号索引需要修改的节点数据
        HeroNode1 temp = head.next;
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

    //删除节点(双向链表中无需辅助节点,可以实现自我删除)
    public void del(int no) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode1 temp = head.next;     //双链表不需要辅助节点,直接自我删除
        boolean flag = false;
        while (true) {
            if (temp == null) {         //已经到了链表的最后
                break;
            }
            if (temp.no == no) {        //删除时机
                flag = !flag;
                break;
            }
            temp = temp.next;
        }
        //出了while只有两种情况:找到该节点,或者该节点不存在
        if (flag) {
            //删除操作
            //temp.next = temp.next.next;//【单链表】
            temp.pre.next = temp.next;
            //需要判断是否到尾部,否则temp.next会出现空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("不存在该节点！");
        }
    }

    //查询对应节点信息
    public void get(int no) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
        }
        HeroNode1 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = !flag;
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
}

//定义HeroNode，每一个HeroNode就是一个节点
class HeroNode1 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode1 next;             //指向下一个节点(默认为null)
    public HeroNode1 pre;              //指向前一个节点(默认为null)

    //创建构造器
    public HeroNode1(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示效果，重写toString
    @Override
    public String toString() {
        return "HeroNode1{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}