public class variable01 {
    public static void main(String[] args){
        //intռ�ĸ��ֽڣ�doubleռ8���ֽڣ�
        int age = 20;
        double score = 88.9;
        char gender = '��';
        String name = "king";
        System.out.println(gender);
        System.out.println(name);

        //����������
        double num1 = 2.7;
        double num2 = 8.1 / 3;
        if(num1 == num2 ){
            System.out.println("���");
        }else{
            System.out.println("num2���޽ӽ�2.7����Ҫȡ�������Ĳ�ֵ�����ж�");
        }
        if(Math.abs(num1-num2) > 0.00001){
            System.out.println("��ֵ�ǳ�С���ﵽ���ǹ鶨�ľ��ȣ���Ϊ��ȡ�");
        }
    }
}
