public class data01 {
   public static  void main(String[] agrs){
       String s5 = "123";
       //使用基本数据类型对应的包装类
       int num1 = Integer.parseInt(s5);
       double num2 = Double.parseDouble(s5);
       float num3 = Float.parseFloat(s5);
       long num4 = Long.parseLong(s5);
       byte num5 = Byte.parseByte(s5);
       boolean num6 = Boolean.parseBoolean(s5);
       short num7 = Short.parseShort(s5);
       System.out.println(num1);
       System.out.println(num2);
       System.out.println(num3);
       System.out.println(num4);
       System.out.println(num5);
       System.out.println(num6);
       System.out.println(num7);
       //把字符串的第一个字符得到
       System.out.println(s5.charAt(0));
   }
}
