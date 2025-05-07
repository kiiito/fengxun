public class hanlouta {
    public static void main(String[] agrs) {
     Tower tower = new Tower();
     tower.move(2,'A','B','C');
    }
}
 class Tower{
    //num表示移动的次数，abc表示塔
    public void move(int num,char a,char b,char c){
        //如果只有一个盘，num = 1
        if(num == 1){
            System.out.println(a + "->" + c);
        }else {
            //如果有多个盘，可以看作是两个，最下面的盘和上面的盘(num - 1)
            //最上面的盘移动到b,借助c
            move((num - 1),a,c,b);
            //再把最下面的盘移动到c
            System.out.println(a + "->" + c);
            //再把b盘的移动到c,借助a
            move((num - 1),b,a,c);

        }
    }
 }
