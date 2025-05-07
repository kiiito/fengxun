package com.hucong.collection_.map_;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"all"})
public class MapExercise {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("001",new Staff("eula","001",100000));
        map.put("002",new Staff("ganYu","002",80000));
        map.put("003",new Staff("hu","003",1000000));
        Set keySet = map.keySet();
        for (Object key : keySet) {
             // 必须要向下转型才能调用staff的方法
            Staff staffs = (Staff) map.get(key);
            if (staffs.getSalary() >= 100000){
                System.out.println(staffs);
            }
        }
        System.out.println("================================================");
        Set entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            //先转型
            Map.Entry entry =  (Map.Entry) iterator.next();//HashMap$Node
            //再将entry.getValue()转成staff
            Staff staff = (Staff) entry.getValue();
            if (staff.getSalary() >= 100000){
                System.out.println(staff);
            }
        }
    }
}
class Staff{
    private String name;
    private String id;
    private double salary;

    public Staff(String name, String id, double salary) {
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

    @Override
    public String toString() {
        return "staff{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", salary=" + salary +
                '}';
    }
}