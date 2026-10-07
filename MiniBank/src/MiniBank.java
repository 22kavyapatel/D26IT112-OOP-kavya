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

        sc.nextLine();

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        if (!Validator.isValidName(name)) {
            System.out.println("Invalid name.");
            return;
        }

        System.out.print("Enter customer email: ");
        String email = sc.nextLine();

        if (!Validator.isValidEmail(email)) {
            System.out.println("Invalid email.");
            return;
        }

        Customer customer =
                new Customer(customerId, name, email);

        System.out.print("Enter city: ");
        String city = sc.nextLine();

        System.out.print("Enter state: ");
        String state = sc.nextLine();

        Customer.Address address =
                new Customer.Address(city, state);

        customer.setAddress(address);

        int accountId = accountCount + 1001;

        accounts[accountCount] =
                new Account(accountId, customer);

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

            System.out.println("\n----------------------");

            System.out.println(
                    StatementFormatter.format(accounts[i])
            );

            Customer customer =
                    accounts[i].getCustomer();

            System.out.println("Address: "
                    + customer.getAddress());

            if (customer instanceof Customer) {
                System.out.println(
                        "Customer is an instance of Customer."
                );
            }
        }
    }
}