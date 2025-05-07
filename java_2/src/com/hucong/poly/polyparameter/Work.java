package com.hucong.poly.polyparameter;

public class Work extends Employee{
    public Work(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double getAnnual() {
        return super.getAnnual();
    }
    public void work01(){
        System.out.println( "普通员工" +getName() + "正在打工");
    }
}
