public class recursion {
    public static void main(String[] agrs) {
     test a = new test();
     int b = a.sum(6);
     System.out.println(b);
     int c = a.count(9);
        System.out.println(c);
    }
}
class test {
    public int sum(int n){
        if(n >= 1) {
            if (n == 1 || n == 2) {
                return 1;
            } else {
                return sum(n - 1) + sum(n - 2);
            }
        }else{
            System.out.println("请输出正确的数。");
            return -1;
        }
        public int count(int n){
            if(n >= 1) {
                if (n == 10) {
                    return 1;
                } else {
                    return (count(n + 1) + 1) * 2;
                }
            }else {
                return -1;
            }
        }
    }
