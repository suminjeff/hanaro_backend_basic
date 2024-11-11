package problem3.accounts;


// 마이너스 통장
public class OverdraftAccount extends Account {

    public OverdraftAccount(int accountNumber, String owner, int balance) {
        super(accountNumber, "마이너스", owner, balance);
    }

    @Override
    public void transfer(int amount, Account to) throws Exception {
        this.setBalance(this.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
    }

    @Override
    public void withdraw(int amount) {
        this.setBalance(this.getBalance() - amount);
    }

    @Override
    public String toString() {
        return String.format("%s 통장 - 잔액 : %s원", this.getAccountName(), df.format(this.getBalance()));
    }
}
