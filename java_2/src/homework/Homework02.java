package homework;

public class Homework02 {
    public static void main(String[] args) {
        Car car = new Car(30);
        Car car2 = new Car(50);
        Car car3 = new Car(-1);
        car.flow();
        car2.flow();
        car3.flow();
    }
}
class Car{
    private double temperature;

    public Car(double temperature) {
        this.temperature = temperature;
    }

    class Air{
        public void xx(){
            if(temperature > 40.0){
                System.out.println("吹冷风");
            }else if(temperature < 0){
                System.out.println("吹热风");
            }else {
                System.out.println("关闭空调");
            }
        }
    }
    public void flow(){
        Air air = new Air();
        air.xx();
    }
//    public Air getAir(){
//        return new Air();
//    }
}