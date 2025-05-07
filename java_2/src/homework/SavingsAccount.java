package homework;

public class SavingsAccount extends BankAccount{

  private int count = 3;
  private double rate = 0.03;

    public SavingsAccount(double balance) {
        super(balance);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public void deposit(double amount) {
        if(count > 0){
            super.deposit(amount);
            count--;
        }else {
            super.deposit(amount - 1);
        }
    }

    @Override
    public void withdraw(double amount) {
        if(count > 0){
            super.deposit(amount);
            count--;
        }else {
            super.deposit(amount - 1);
        }
    }

    public void earnMonthlyInterest(){
        count = 3;
        super.deposit(getBalance() * rate); //deposit是存入的方法，而利率就可以借用存入的方法
    }
    public void info(){
        System.out.println("余额\t" + getBalance());
    }

}
