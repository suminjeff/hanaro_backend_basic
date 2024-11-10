package problem3.accounts;

import java.text.DecimalFormat;

// 계좌 추상 클래스
public abstract class Account {
    protected int accountNumber;  // 계좌번호
    protected String accountName;  // 계좌명
    protected String owner;  // 소유자 이름
    protected int balance;
    protected boolean transferable;
    protected boolean withdrawable;

    Account(int accountNumber, String accountName, String owner, int balance, boolean transferable, boolean withdrawable) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.owner = owner;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        if (withdrawable) {
            if (balance >= amount) {
                balance -= amount;
            } else {
                System.out.printf("잔액이 부족합니다! (잔액: %d원)\n", amount);
            }
        } else {
            System.out.println("출금할 수 없는 통장입니다.");
        }
    }

    public void transfer(int amount, Account to) {
        if (transferable) {
            if (balance >= amount) {
                withdraw(amount);
                to.deposit(amount);
            }
        } else {
            System.out.println("이체할 수 없는 통장입니다.");
        }
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###");
        return String.format("%s (계좌번호: %s, 잔액: %s원, 예금주: %s)", accountName, accountNumber, df.format(balance), owner);
    }
}
