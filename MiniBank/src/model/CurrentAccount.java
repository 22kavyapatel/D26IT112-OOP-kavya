package model;

public class CurrentAccount extends Account {

    public CurrentAccount(int accountId, Customer customer) {
        super(accountId, customer);
    }

    @Override
    public double interestRate() {
        return 2.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return amount > 0 && amount <= getBalance() + 5000;
    }
}