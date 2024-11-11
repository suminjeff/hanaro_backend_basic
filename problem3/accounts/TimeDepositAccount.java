package problem3.accounts;


// 정기 예금
public class TimeDepositAccount extends Account {
    private int depositTerm;  // 예치 기간(납부 개월 수)
    private int depositMonths;
    private double interestRate;

    public TimeDepositAccount(int accountNumber, String owner, int balance, double interestRate, int depositTerm) {
        super(accountNumber, "정기예금", owner, balance, false, false);
        this.depositTerm = depositTerm; this.interestRate = interestRate;
    }

    public int getDepositTerm() {
        return depositTerm;
    }

    public void setDepositTerm(int depositTerm) {
        this.depositTerm = depositTerm;
    }

    public int getDepositMonths() {
        return depositMonths;
    }

    public void setDepositMonths(int depositMonths) {
        this.depositMonths = depositMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }
}
