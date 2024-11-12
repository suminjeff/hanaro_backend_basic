package problem3.accounts;


// 정기 예금
public class TimeDepositAccount extends Account {
    private int depositTerm;  // 예치 기간
    private double interestRate;

    public TimeDepositAccount(int accountNumber, String owner, int balance, int depositTerm) {
        super(accountNumber, "정기예금", owner, balance);
        this.depositTerm = depositTerm;
        this.interestRate = calculateInterestRate(depositTerm);
    }

    public double calculateInterestRate(int month) {
        if (month >= 48) return 2.9;
        else if (month >= 36) return 2.9;
        else if (month >= 24) return 2.9;
        else if (month >= 12) return 3.35;
        else if (month >= 9) return 3.35;
        else if (month >= 6) return 3.4;
        else if (month >= 3) return 3.35;
        else if (month >= 1) return 3.0;
        else return 0.0;
    }

    public double calculateDepositProceeds(int month) {
        return Math.round(this.getBalance() * (1 + calculateInterestRate(month) * 0.01));
    }

    public int getDepositTerm() {
        return depositTerm;
    }

    public void setDepositTerm(int depositTerm) {
        this.depositTerm = depositTerm;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(int amount) throws Exception {
        throw new Exception("출금할 수 없는 통장입니다.");
    }

    @Override
    public void transfer(int amount, Account to) throws Exception {
        throw new Exception("이체할 수 없는 통장입니다.");
    }

    @Override
    public String toString() {
        return String.format("%s 통장 (계좌번호: %s, 예치금: %s원, 예금주: %s)", this.getAccountName(), this.getAccountNumber(), df.format(this.getBalance()), this.getOwner());
    }
}
