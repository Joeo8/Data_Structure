package LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 23:20
 * Description: Josephu
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList CS = new CircleSingleLinkedList();
        CS.addBoy(10);
        CS.showBoy();
        System.out.println("================Josephu=================");
        CS.countBoy(2,3,10);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建first节点，当前没有编号
    private Boy first = new Boy(-1);

    //添加Boy节点，构造环形链表
    public void addBoy(int num) {
        if (num <= 0) {
            System.out.println("num值有误！");
            return;
        }
        Boy curBoy = null; //辅助节点,方便遍历,无实际意义
        for (int i = 1; i <= num; i++) {         //用for循环自动加入i个节点
            //根据编号创建Boy节点
            Boy boy = new Boy(i);
            //如果是第一个Boy,使其本身成为环状
            if (i == 1) {
                first = boy;
                first.setNext(first);  //构成环状
                curBoy = first; //让curBoy辅助变量指向第一个节点
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;  //将辅助节点后移
            }
        }
    }

    //遍历环形链表
    public void showBoy() {
        //判断环形链表是否为空
        if (first == null) {
            System.out.println("当前链表为空！");
            return;
        }
        //创建辅助节点并遍历链表
        Boy curBoy = first;
        while (true) {
            System.out.println("Boy : " + curBoy.getNo());
            if (curBoy.getNext() == first) {                     //环形链表遍历完毕
                break;
            }
            curBoy = curBoy.getNext();                          //获得当前节点的下一个节点
        }
    }

    //根据用户的输入,计算Boy结点出圈序列
    public void countBoy(int startNo, int countNum, int totalNum) {
        //验证数据合理性
        if (first == null || startNo < 1 || startNo > totalNum) {
            System.out.println("参数设计不合理！");
            return;
        }
        //创建辅助指针
        Boy helpBoy = first;
        //循环遍历时helpBoy指向环形链表中的最后一个节点
        while (true) {
            if (helpBoy.getNext() == first) {
                break;
            }
            helpBoy = helpBoy.getNext();
        }
        //开始计数前先将first和helpBoy同时移动starNum-1下,归位
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helpBoy = helpBoy.getNext();
        }
        //让first和helpBoy同时移动countNum-1下(循环操作,直到链表中只剩一个节点)
        while (true) {
            if (helpBoy == first){           //链表中只剩一个节点
                break ;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helpBoy = helpBoy.getNext();
            }
            System.out.println(first.getNo() + " Out");
            //将first指向下一个节点,并将helperBoy指向新的first(删除后仍保持环形)
            first = first.getNext();
            helpBoy.setNext(first);
        }
        System.out.println("=================Josephu===================");
        System.out.println(first.getNo() + " Out");
    }
}

//创建一个Boy节点
class Boy {
    private int no; //编号
    private Boy next;  //指向下一个节点,默认为null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}