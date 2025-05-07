package com.hucong.generic;

import java.util.ArrayList;
import java.util.Comparator;

public class GenericExercise02 {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("ganYu",10000,new MyData(2002,8,18)));
        employees.add(new Employee("eula",30000,new MyData(2003,6,19)));
        employees.add(new Employee("ganYu",80000,new MyData(2002,9,18)));
        for (Employee employee : employees) {
            System.out.println(employee);

        }
        employees.sort(new Comparator<Employee>() {

            @Override
            public int compare(Employee o1, Employee o2) {
                //±È½Ïname
                int i = o1.getName().compareTo(o2.getName());
                if(i != 0){
                    return i;
                }
                return o1.getBirthday().compareTo(o2.getBirthday());
            }

        });
        System.out.println("=========================");
        for (Employee employee : employees) {
            System.out.println(employee);

        }
    }
}
class Employee{
    private String name;
    private double sal;
    private MyData birthday;

    public Employee(String name, double sal, MyData birthday) {
        this.name = name;
        this.sal = sal;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public MyData getBirthday() {
        return birthday;
    }

    public void setBirthday(MyData birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", birthday=" + birthday +
                '}';
    }
}
class MyData implements Comparable<MyData> {
    private int month;
    private int year;
    private int day;

    public MyData(int year,int month, int day) {
        this.month = month;
        this.year = year;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public int compareTo(MyData o) {
        int yearMinus = year - o.getYear();
        if (yearMinus != 0){
            return yearMinus;
        }
        int monthMinus = month - o.getMonth();
        if (monthMinus != 0){
            return monthMinus;
        }
        return day - o.getDay();
    }

    @Override
    public String toString() {
        return "MyData{" +
                " year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}