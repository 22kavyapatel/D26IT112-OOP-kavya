package model;

public abstract class Account
        implements Transactable, InterestBearing {

    private int accountId;
    private Customer customer;
    private long balance;

    public Account(int accountId, Customer customer) {
        this.accountId = accountId;
        this.customer = customer;
        this.balance = 0;
    }

    public int getAccountId() {
        return accountId;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public long getBalance() {
        return balance;
    }

    protected void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(long amount) {

        if (amount > 0) {
            balance = balance + amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    @Override
    public boolean withdraw(long amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return false;
        }

        if (!canWithdraw(amount)) {
            System.out.println("Withdrawal not allowed.");
            return false;
        }

        balance = balance - amount;

        System.out.println("Amount withdrawn successfully.");

        return true;
    }

    public abstract boolean canWithdraw(long amount);

    @Override
    public String toString() {

        return "Account ID: " + accountId
                + ", Customer: " + customer.getName()
                + ", Balance: " + balance;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Account)) {
            return false;
        }

        Account other = (Account) obj;

        return accountId == other.accountId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(accountId);
    }
}