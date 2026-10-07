public class Account {

    private int accountId;
    private Customer customer;
    private double balance;

    public Account(int accountId, Customer customer) {
        this.accountId = accountId;
        this.customer = customer;
        this.balance = 0.0;
    }

    public int getAccountId() {
        return accountId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {

        if (amount > 0) {
            balance = balance + amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance = balance - amount;
            System.out.println("Amount withdrawn successfully.");
        }
    }

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