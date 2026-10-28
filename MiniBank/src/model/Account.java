package model;

import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public abstract class Account
        implements Transactable, InterestBearing,
                   Comparable<Account> {

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

    // Deposit money into the account
    @Override
    public synchronized void deposit(long amount)
            throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero.");
        }

        setBalance(getBalance() + amount);
    }

    // Withdraw money from the account
    @Override
    public synchronized boolean withdraw(long amount)
            throws InsufficientFundsException,
                   InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero.");
        }

        if (!canWithdraw(amount)) {
            long shortfall = Math.max(0, amount - balance);

            throw new InsufficientFundsException(
                    "Withdrawal failed.", shortfall);
        }

        balance -= amount;

        System.out.println("Amount withdrawn successfully.");

        return true;
    }

    // Transfer money to another account
    public void transfer(Account to, long amount)
            throws BankException {

        try {
            if (to == null) {
                throw new BankException(
                        "Destination account not found.");
            }

            withdraw(amount);
            to.deposit(amount);

            System.out.println("Transfer successful.");

        } catch (BankException e) {
            throw e;

        } finally {
            System.out.println(
                    "Transfer operation completed.");
        }
    }

    // Compare accounts by account number
    @Override
    public int compareTo(Account other) {
        return Integer.compare(
                this.accountId, other.accountId);
    }

    // Every account type implements this method
    public abstract boolean canWithdraw(long amount);

    // Display account information
    @Override
    public String toString() {
        return "Account ID: " + accountId
                + ", Customer: " + customer.getName()
                + ", Balance: " + balance;
    }

    // Compare accounts using account ID
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