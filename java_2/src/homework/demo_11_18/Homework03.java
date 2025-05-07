package homework.demo_11_18;

public class Homework03 {
    public static void main(String[] args) {
        Fisher fisher = new Fisher();
        fisher.way();
        Bird bird = new Bird();
        bird.way();
        Frog frog = new Frog();
        frog.way();
    }
}
class Bird implements land_Animal {
    @Override
    public void way() {
        System.out.println("鸟在陆地生活");
    }
}
class Fisher implements water_Animal {
    @Override
    public void way() {
        System.out.println("鱼在水中生活");
    }
}
class Frog implements land_Animal,water_Animal {
    @Override
    public void way() {
        System.out.println("青蛙是两栖动物");
    }
}