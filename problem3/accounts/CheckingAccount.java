package problem3.accounts;


// 자유입출금 계좌
public class CheckingAccount extends Account{
    private double interestRate;

    public CheckingAccount(int accountNumber, String owner, int balance, double interestRate) {
        super(accountNumber, "자유입출금", owner, balance, true, true);
        this.interestRate = interestRate;
    }

    @Override
    public void transfer(int amount, Account to) {}

    @Override
    public void withdraw(int amount) {}

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
