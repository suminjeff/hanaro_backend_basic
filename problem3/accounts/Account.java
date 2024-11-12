package problem3.accounts;

import java.text.DecimalFormat;

// 계좌 추상 클래스
public abstract class Account implements Transferable, Withdrawable {
    static DecimalFormat df = new DecimalFormat("#,###");
    protected int accountNumber;  // 계좌번호
    protected String accountName;  // 계좌명
    protected String owner;  // 소유자 이름
    protected int balance;

    Account(int accountNumber, String accountName, String owner, int balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("%s에 %s원이 입금되었습니다!\n", accountName, df.format(amount));
        }
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String toString() {
        return String.format("%s 통장 (계좌번호: %s, 잔액: %s원, 예금주: %s)", accountName, accountNumber, df.format(balance), owner);
    }
}
