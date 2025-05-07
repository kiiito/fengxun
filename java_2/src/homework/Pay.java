package homework;

import java.util.Scanner;

public class Pay {
    public static void main(String[] args) {
        int shirtPrice,shirtNu,shoePrice,shoeNu;
        final double DI = 0.8;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入衬衣的价格");
        shirtPrice = scanner.nextInt();
        System.out.println("请输入衬衣的数量");
        shirtNu = scanner.nextInt();
        System.out.println("请输入运动鞋的价格");
        shoePrice = scanner.nextInt();
        System.out.println("请输入运动鞋的数量");
        shoeNu = scanner.nextInt();

        System.out.println("**********消费单*********");
        System.out.println("购买物品\t" + "单价\t" + "数量\t");
        System.out.println("衬衣\t\t" + shirtPrice + "元\t" + "数量\t" + shirtNu);
        System.out.println("运动鞋\t" + shoePrice + "元\t" + "数量\t" + shoeNu);
        System.out.println("折扣\t" + DI);
    }
}
