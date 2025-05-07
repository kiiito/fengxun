package com.hucong.abstract_;

public class AbstractExercise {
    public static void main(String[] args) {
        Manager manager = new Manager("jack","001",10000,1000);
        manager.work();
    }
}
abstract class Employee{
    private String name;
    private String id;
    private double salary;


    abstract void work();

    public Employee(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
class Manager extends Employee{
    private double bonus;

    public Manager(String name, String id, double salary, double bonus) {
        super(name, id, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    void work() {
        System.out.println("经理" + getName() + "工作中");
    }
}
