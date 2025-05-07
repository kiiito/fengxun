public class variableParameters {
    public static void main(String[] agrs) {
        T t = new T();
      String num1 = t.cood("jack",89.0,98.0);
        System.out.println(num1);
    }
}
class T{
    public String cood(String name, double... num){
        int res = 0;
        for(int i=0;i<num.length;i++){
            res += num[i];
        }
        return name + num.length + "门课总分为" + res;
    }
}