package homework;

public class Person02 {

    public static void main(String[] args) {
        Person03 person03 = new Person03("唐僧",new Horse());
        person03.common();
        person03.passRiver();
    }
}
interface Vehicles{
    void work();
}
class Horse implements Vehicles{

    @Override
    public void work() {
        System.out.println("马在跑");
    }
}
class Boat implements Vehicles{

    @Override
    public void work() {
        System.out.println("乘船");
    }
}
class Tool{
    private Tool() {
    }

    private static Horse horse = new Horse();
    public static Horse getHorse() {
        //马始终是同一匹马
        return horse;
    }
    public static Boat getBoat() {
        return new Boat();
    }
}
class Person03{
    private String name;
    private Vehicles vehicles;


    public Person03(String name, Vehicles vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }
    public void  passRiver(){
//       Boat tool = Tool.getBoat();
//        tool.work();
//        if(vehicles == null)
        //判断当前的 vehicles 是否为空和是不是Boat
        if (!(vehicles instanceof Boat)){
            vehicles = Tool.getBoat();
        }
        vehicles.work();
    }

    public void common(){
        if (!(vehicles instanceof Horse)){
            vehicles = Tool.getHorse();
        }
        vehicles.work();
        }

}
