package examination;

public class dome08 {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(3, 4, 5);
        triangle.area();
        triangle.perimeter();
        Triangle triangle1 = new Triangle(15, 16, 18);
        triangle1.area();
        triangle1.perimeter();
    }
}
class Triangle{
    public int x,y,z;

    public Triangle(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Triangle(){

    }
    public void area(){
        double s = (x+y+z)/2;
        System.out.println("三角形的面积为 = " + Math.sqrt(s*(s-x)*(s-y)*(s-z)));
    }
    public void perimeter(){
        System.out.println("三角形的周长为 =" + (x+y+z));
    }
}
