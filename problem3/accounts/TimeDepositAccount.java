package problem3.accounts;


// 정기 예금
public class TimeDepositAccount extends Account {
    private int depositTerm;  // 예치 기간
    private int depositMonths = 0;  // 현재까지 납부 개월 수
    private double interestRate;

    public TimeDepositAccount(int accountNumber, String owner, int balance, int depositTerm) {
        super(accountNumber, "정기예금", owner, balance);
        this.depositTerm = depositTerm;
        this.interestRate = calculateInterestRate(depositTerm);
    }

    public double calculateInterestRate(int month) {
        if (month < 1) return 0.0;
        else if (month < 3) return 3.0;
        else if (month < 6) return 3.35;
        else if (month < 9) return 3.4;
        else if (month < 12) return 3.35;
        else if (month < 24) return 3.35;
        else if (month < 36) return 2.9;
        else if (month < 48) return 2.9;
        else return 2.9;
    }

    public double calculateDepositProceeds(int month) {
        return this.getBalance() * (1 + calculateInterestRate(month) * 0.01);
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

    @Override
    public void deposit(int amount) {
        setBalance(getBalance() + amount);
        setDepositMonths(getDepositMonths() + 1);
        System.out.printf("%s에 %s원이 입금되었습니다!\n", this.getAccountName(), df.format(amount));
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
