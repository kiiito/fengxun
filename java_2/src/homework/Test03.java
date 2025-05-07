package homework;

public class Test03 {
    public static void main(String[] args) {
        SavingsAccount test01 = new SavingsAccount(1000);
        test01.deposit(100);
        test01.deposit(100);
        test01.deposit(100);
        test01.deposit(100);
//        SavingsAccount test02 = (SavingsAccount) test01;
        test01.earnMonthlyInterest();
        test01.withdraw(1000);
        test01.info();

    }

}
