package homework.demo_11_18;

public class Homework06 {
    public static void main(String[] args) {
        Area area = new Area("黄色",6);
        area.getArea();
    }
}
abstract class Shape{
    String color;

    public Shape(String color) {
        this.color = color;
    }
    public abstract void getArea();
}
class Area extends Shape{
    double l;

    public Area(String color, double l) {
        super(color);
        this.l = l;
    }

    @Override
    public void getArea() {
        System.out.println("圆的面积 " + 3.14 * l * l);
    }
}
