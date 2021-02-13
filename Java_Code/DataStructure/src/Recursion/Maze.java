package Recursion;

/**
 * Created with IntelliJ IDEA.
 * User: Joeo8
 * Time: 14:20
 * Description: Maze Implementation
 */
public class Maze {
    public static void main(String[] args) {
        //创建一个二位数组,表示地图
        int[][] map = new int[8][7];
        //使用1表示墙,四周全部设为1
        //设置上下墙面
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //设置左右墙面
        for (int i = 1; i < 7; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置相应挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        System.out.println("====================Map=====================");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

}
