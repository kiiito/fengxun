package homework;

public class CheckingAccount extends BankAccount{

    public CheckingAccount(double balance) {
        super(balance);

    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount - 1);
        System.out.println(getBalance());
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount - 1);
        System.out.println(getBalance());
    }
}
