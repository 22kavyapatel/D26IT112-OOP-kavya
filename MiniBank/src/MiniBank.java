import java.util.Scanner;

public class MiniBank {

    static Scanner sc = new Scanner(System.in);

    static Account[] accounts = new Account[10];
    static int accountCount = 0;

    public static void main(String[] args) {

        BankInfo bank = new BankInfo("MiniBank", "CHARUSAT");

        System.out.println("================================");
        System.out.println("          " + bank.name());
        System.out.println("       Branch: " + bank.branch());
        System.out.println("================================");

        boolean running = true;

        while (running) {

            System.out.println("\n----- MiniBank Menu -----");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
        System.out.println("5. Show Accounts");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    System.out.println("Transfer will be added later.");
                    break;

                case 5:
                    showAccounts();
                    break;

                case 6:
                    System.out.println("Thank you for using MiniBank!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }

    static void createAccount() {

        if (accountCount == accounts.length) {
            System.out.println("Account limit reached.");
            return;
        }

        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();

        System.out.print("Enter customer name: ");
        String name = sc.next();

        if (!Validator.isValidName(name)) {
            System.out.println("Invalid name.");
            return;
        }

        System.out.print("Enter customer email: ");
        String email = sc.next();

        if (!Validator.isValidEmail(email)) {
            System.out.println("Invalid email.");
            return;
        }

        Customer customer =
                new Customer(customerId, name, email);

        System.out.print("Enter city: ");
        String city = sc.next();

        System.out.print("Enter state: ");
        String state = sc.next();

        Customer.Address address =
                new Customer.Address(city, state);

        customer.setAddress(address);

        System.out.println("\nSelect Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.println("3. Fixed Deposit Account");

        System.out.print("Enter type: ");
        int type = sc.nextInt();

        int accountId = accountCount + 1001;

        Account account;

        if (type == 1) {

            account = new SavingsAccount(accountId, customer);

        } else if (type == 2) {

            account = new CurrentAccount(accountId, customer);

        } else if (type == 3) {

            account = new FixedDepositAccount(accountId, customer);

        } else {

            System.out.println("Invalid account type.");
            return;
        }

        accounts[accountCount] = account;
        accountCount++;

        System.out.println("Account created successfully.");
        System.out.println("Account ID: " + accountId);
    }

    static Account findAccount(int accountId) {

        for (int i = 0; i < accountCount; i++) {

            if (accounts[i].getAccountId() == accountId) {
                return accounts[i];
            }
        }

        return null;
    }

    static void deposit() {

        System.out.print("Enter account ID: ");
        int accountId = sc.nextInt();

        Account account = findAccount(accountId);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        if (!Validator.isValidAmount(amount)) {
            System.out.println("Invalid amount.");
            return;
        }

        account.deposit(amount);
    }

    static void withdraw() {

        System.out.print("Enter account ID: ");
        int accountId = sc.nextInt();

        Account account = findAccount(accountId);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        if (!Validator.isValidAmount(amount)) {
            System.out.println("Invalid amount.");
            return;
        }

        account.withdraw(amount);
    }

    static void showAccounts() {

        if (accountCount == 0) {
            System.out.println("No accounts found.");
            return;
        }

        for (int i = 0; i < accountCount; i++) {

            Account account = accounts[i];

            System.out.println("\n----------------------");

            System.out.println("Account ID: "
                    + account.getAccountId());

            System.out.println("Customer: "
                    + account.getCustomer().getName());

            System.out.println("Balance: "
                    + account.getBalance());

            System.out.println("Interest Rate: "
                    + account.interestRate() + "%");

            if (account instanceof SavingsAccount) {

                System.out.println("Type: Savings Account");

            } else if (account instanceof CurrentAccount) {

                System.out.println("Type: Current Account");

            } else if (account instanceof FixedDepositAccount) {

                System.out.println("Type: Fixed Deposit Account");
            }
        }
    }
}