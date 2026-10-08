package model;

public class FixedDepositAccount extends Account {

    public FixedDepositAccount(int accountId, Customer customer) {
        super(accountId, customer);
    }

    @Override
    public double interestRate() {
        return 7.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return false;
    }
}