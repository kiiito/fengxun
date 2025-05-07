package homework;

public class Test04 {
    public static void main(String[] args) {
        Person01 [] person01s = new Person01[4];
        person01s[0] = new Student("jack",'ÄÐ',18,001);
        person01s[1] = new Student("tom",'ÄÐ',16,002);
        person01s[2] = new Teacher01("mei",'Å®',32,10);
        person01s[3] = new Teacher01("li",'ÄÐ',29,8);
        Test04 test04 = new Test04();
        test04.bubbleSort(person01s);
        for (int i = 0; i < person01s.length; i++) {
            System.out.println(person01s[i]);
            test04.test02(person01s[i]);
        }
//        System.out.println("================");
//        for (int i = 0; i < person01s.length; i++){
//            test04.test02(person01s[i]);
//        }
    }
    public void bubbleSort(Person01 [] person01s){
        Person01 temp = null;
        for (int i = 0; i < person01s.length -1;i++){
            for (int j = 0; j < person01s.length - i - 1; j++){
                if(person01s[j].getAge() > person01s[j + 1].getAge()){
                    temp = person01s[j];
                    person01s[j] = person01s[j + 1];
                    person01s[j + 1] = temp;
                }
            }
        }
    }
    public void into_01(Person01[] person01s){
        for (int i = 0; i < person01s.length ; i++) {
            if(person01s[i] instanceof Student){
                ((Student) person01s[i]).study();
            }else {
                ((Teacher01) person01s[i]).teacher();
            }
        }
    }
    public void test02(Person01 p){
        if(p instanceof Student){
            ((Student) p).study();
        }else if(p instanceof Teacher01){
            ((Teacher01) p).teacher();
        }else {
            System.out.println("  ");
        }
    }

}
