package problem3.accounts;

import java.util.Calendar;

// 마이너스 통장
public class OverdraftAccount extends Account {
    static double overDraftLimit = Double.NEGATIVE_INFINITY;
    private int overDraftAmount = 0;

    public OverdraftAccount(int accountNumber, String owner, int balance) {
        super(accountNumber, "마이너스", owner, balance ,true, true);
    }

    @Override
    public void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            overDraftAmount += amount - balance;
            balance = 0;
        }
    }

    @Override
    public void transfer(int amount, Account to) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            overDraftAmount += amount - balance;
            balance = 0;
        }
    }
}
