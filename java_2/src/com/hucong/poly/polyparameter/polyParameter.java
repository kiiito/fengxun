package com.hucong.poly.polyparameter;

public class polyParameter {
    public static void main(String[] args) {
        Employee jack = new Work("jack",3000);
        Employee tom = new Manager("tom",10000,50000);
        polyParameter polyParameter = new polyParameter();
        polyParameter.showEmpAnnual(tom);
        polyParameter.showEmpAnnual(jack);
        polyParameter.testWork(jack);
        polyParameter.testWork(tom);
    }
        //实现获取任何员工对象的年工资，并在main方法中调用该方法
        public void showEmpAnnual(Employee e){//传进的形参类型为父类类型，实参允许为子类类型
            System.out.println(e.getAnnual());
    }
    public void testWork(Employee e){
        if(e instanceof Work){
            ((Work) e).work01();
        }else if(e instanceof Manager){
            ((Manager) e).manage();
        }
    }
}
