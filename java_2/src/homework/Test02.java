package homework;

public class Test02 {
    public static void main(String[] args) {
        Staff manager = new Manager("jack",3000,30,1.2);
        manager.info();
        Staff employee = new Employees("tom",3000,30,1);
        employee.info();
    }
}
