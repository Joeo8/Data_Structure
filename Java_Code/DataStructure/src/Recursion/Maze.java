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
        setWay(map,1,1);
        System.out.println("====================Over=====================");
         for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    //使用递归回溯走出迷宫
    /*说明:
     *   map表示地图
     *   i,j表示起始位 (1,1)
     *   如果能到达map[6][5],则说明通路已经找到
     *   约定:map[i][j]的状态值为0表示没有走过;为1表示墙体;为2表示通路可以走;为3表示死路
     *   最后设置策略 下 --> 右 --> 上 --> 左
     * */

    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {  //通路已经找到,Game Over
            return true;
        } else {
            if (map[i][j] == 0) {                         //如果这个点还没有走
                map[i][j] = 2;                            //设想可以走通,预设为2
                if (setWay(map, i + 1, j)) {            //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {     //向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {     //向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {     //向左走
                    return true;
                }else{
                                                          //说明该点走不通是死路
                    map[i][j] =3 ;
                    return false;
                }
            }else{  //如果这个点已经走过
                return false;
            }
        }
    }
}
