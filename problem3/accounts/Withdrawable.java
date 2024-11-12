package problem3.accounts;

public interface Withdrawable {
    public abstract void transfer(int amount, Account to) throws Exception;
}
