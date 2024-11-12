package problem3.accounts;


// 자유입출금 계좌
public class CheckingAccount extends Account {

    public CheckingAccount(int accountNumber, String owner, int balance) {
        super(accountNumber, "자유입출금", owner, balance);
    }

    @Override
    public void transfer(int amount, Account to) throws Exception {
        if (amount > this.getBalance()) {
            throw new Exception(String.format("잔액이 부족합니다! (잔액: %s원)", df.format(this.getBalance())));
        } else if (amount > 0) {
            this.setBalance(this.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
        }
    }

    @Override
    public void withdraw(int amount) throws Exception {
        if (amount > this.getBalance()) {
            throw new Exception(String.format("잔액이 부족합니다! (잔액: %s원)", df.format(this.getBalance())));
        } else {
            this.setBalance(this.getBalance() - amount);
        }
    }
}
