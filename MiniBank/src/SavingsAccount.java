public class SavingsAccount extends Account {

    public SavingsAccount(int accountId, Customer customer) {
        super(accountId, customer);
    }

    @Override
    public double interestRate() {
        return 4.0;
    }

    @Override
    public boolean canWithdraw(double amount) {
        return amount > 0 && amount <= getBalance();
    }
}