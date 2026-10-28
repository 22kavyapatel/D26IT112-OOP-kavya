import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Account;
import model.AccountStore;
import model.Customer;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;

import service.StatementFormatter;

import util.BankInfo;
import util.Validator;
import util.WithdrawRule;
import util.BankResource;

import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import exception.AccountNotFoundException;

import static util.Validator.isValidEmail;
import static util.Validator.isValidName;

public class MiniBank {

    static Scanner sc = new Scanner(System.in);
    static AccountStore store = new AccountStore();

    public static void main(String[] args) {

        if (args.length > 0
                && args[0].equals("--milestone2-demo")) {
            demonstrateCollections();
            return;
        }

        BankInfo bank = new BankInfo("MiniBank", "CHARUSAT");

        System.out.println("================================");
        System.out.println("          " + bank.name());
        System.out.println("       Branch: " + bank.branch());
        System.out.println("================================");

        // Anonymous class
        WithdrawRule rule1 = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return account.canWithdraw(amount);
            }
        };

        // Lambda expression
        WithdrawRule rule2 =
                (account, amount) -> account.canWithdraw(amount);

        System.out.println("Withdraw rule test: "
                + (rule1 != null && rule2 != null));

        demonstrateTryWithResources();

        boolean running = true;

        while (running) {

            System.out.println("\n----- MiniBank Menu -----");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Show Accounts");
            System.out.println("6. Exit");
            System.out.println("7. Show Accounts Sorted by Balance");

            System.out.print("Enter your choice: ");

            try {
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
                        transfer();
                        break;

                    case 5:
                        showAccounts();
                        break;

                    case 6:
                        System.out.println(
                                "Thank you for using MiniBank!");
                        running = false;
                        break;

                    case 7:
                        showAccountsSortedByBalance();
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (InputMismatchException e) {
                System.out.println(
                        "Please enter a valid number.");
                sc.nextLine();

            } finally {
                System.out.println(
                        "Menu operation completed.");
            }
        }

        sc.close();
    }

    // Try-with-resources demonstration
    static void demonstrateTryWithResources() {

        try (BankResource resource = new BankResource()) {

            throw new IllegalStateException("Demo exception");

        } catch (IllegalStateException e) {
            System.out.println(
                    "Handled: " + e.getMessage());
        }
    }

    // Create account
    static void createAccount() {

        try {
            System.out.print("Enter customer ID: ");
            int customerId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter customer name: ");
            String name = sc.nextLine();

            if (!isValidName(name)) {
                System.out.println("Invalid name.");
                return;
            }

            System.out.print("Enter customer email: ");
            String email = sc.nextLine();

            if (!isValidEmail(email)) {
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

            System.out.println("\nSelect Account Type:");
            System.out.println("1. Savings Account");
            System.out.println("2. Current Account");
            System.out.println("3. Fixed Deposit Account");

            System.out.print("Enter type: ");
            int type = sc.nextInt();

            int accountId = 1001 + store.getAll().size();

            Account account;

            if (type == 1) {
                account =
                        new SavingsAccount(accountId, customer);

            } else if (type == 2) {
                account =
                        new CurrentAccount(accountId, customer);

            } else if (type == 3) {
                account =
                        new FixedDepositAccount(accountId, customer);

            } else {
                System.out.println("Invalid account type.");
                return;
            }

            store.add(account);

            store.addTransaction(
                    "Account created: " + accountId);

            System.out.println(
                    "Account created successfully.");
            System.out.println("Account ID: " + accountId);

        } catch (InputMismatchException e) {
            System.out.println(
                    "Invalid input. Please enter the correct data.");
            sc.nextLine();

        } finally {
            System.out.println(
                    "Account creation operation completed.");
        }
    }

    // Find account
    static Account findAccount(int accountId) {
        return store.get(String.valueOf(accountId));
    }

    // Deposit money
    static void deposit() {

        try {
            System.out.print("Enter account ID: ");
            int accountId = sc.nextInt();

            Account account = findAccount(accountId);

            if (account == null) {
                throw new AccountNotFoundException(
                        "Account not found.");
            }

            System.out.print("Enter amount: ");
            long amount = sc.nextLong();

            account.deposit(amount);

            store.addTransaction(
                    "Deposit: Account " + accountId
                    + ", Amount " + amount);

            System.out.println("Deposit successful.");

        } catch (InvalidAmountException e) {
            System.out.println(
                    "Deposit failed: " + e.getMessage());

        } catch (AccountNotFoundException e) {
            System.out.println(
                    "Deposit failed: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers only.");
            sc.nextLine();

        } finally {
            System.out.println(
                    "Deposit operation finished.");
        }
    }

    // Withdraw money
    static void withdraw() {

        try {
            System.out.print("Enter account ID: ");
            int accountId = sc.nextInt();

            Account account = findAccount(accountId);

            if (account == null) {
                throw new AccountNotFoundException(
                        "Account not found.");
            }

            System.out.print("Enter amount: ");
            long amount = sc.nextLong();

            account.withdraw(amount);

            store.addTransaction(
                    "Withdrawal: Account " + accountId
                    + ", Amount " + amount);

            System.out.println("Withdrawal successful.");

        } catch (InsufficientFundsException e) {
            System.out.println(
                    "Withdrawal failed: " + e.getMessage());
            System.out.println(
                    "Shortfall: " + e.getShortfall());

        } catch (InvalidAmountException e) {
            System.out.println(
                    "Withdrawal failed: " + e.getMessage());

        } catch (AccountNotFoundException e) {
            System.out.println(
                    "Withdrawal failed: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers only.");
            sc.nextLine();

        } finally {
            System.out.println(
                    "Withdrawal operation finished.");
        }
    }

    // Transfer money
    static void transfer() {

        try {
            System.out.print("Enter source account ID: ");
            int sourceId = sc.nextInt();

            Account source = findAccount(sourceId);

            if (source == null) {
                throw new AccountNotFoundException(
                        "Source account not found.");
            }

            System.out.print(
                    "Enter destination account ID: ");
            int destinationId = sc.nextInt();

            Account destination = findAccount(destinationId);

            if (destination == null) {
                throw new AccountNotFoundException(
                        "Destination account not found.");
            }

            if (sourceId == destinationId) {
                System.out.println(
                        "Source and destination must differ.");
                return;
            }

            System.out.print("Enter transfer amount: ");
            long amount = sc.nextLong();

            source.transfer(destination, amount);

            store.addTransaction(
                    "Transfer: From " + sourceId
                    + " to " + destinationId
                    + ", Amount " + amount);

        } catch (InsufficientFundsException e) {
            System.out.println(
                    "Transfer failed: " + e.getMessage());
            System.out.println(
                    "Shortfall: " + e.getShortfall());

        } catch (InvalidAmountException e) {
            System.out.println(
                    "Transfer failed: " + e.getMessage());

        } catch (AccountNotFoundException e) {
            System.out.println(
                    "Transfer failed: " + e.getMessage());

        } catch (BankException e) {
            System.out.println(
                    "Transfer failed: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers only.");
            sc.nextLine();

        } finally {
            System.out.println(
                    "Transfer operation finished.");
        }
    }

    // Display all accounts
    static void showAccounts() {

        ArrayList<Account> accounts = store.getAll();

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (Account account : accounts) {

            System.out.println("\n----------------------");

            System.out.println(
                    StatementFormatter.format(account));

            if (account instanceof SavingsAccount) {
                System.out.println("Type: Savings Account");

            } else if (account instanceof CurrentAccount) {
                System.out.println("Type: Current Account");

            } else if (account instanceof FixedDepositAccount) {
                System.out.println(
                        "Type: Fixed Deposit Account");
            }

            System.out.println(
                    "Yearly Interest: "
                    + account.yearlyInterest());
        }
    }

    // Display accounts sorted by balance
    static void showAccountsSortedByBalance() {

        ArrayList<Account> accounts =
                store.sortedByBalance();

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println(
                "\n===== Accounts Sorted by Balance =====");

        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    // Demonstrate HashMap, ArrayList, Comparator and Comparable
    static void demonstrateCollections() {

        System.out.println(
                "===== MiniBank Milestone 2 =====");

        AccountStore demoStore = new AccountStore();

        Customer c1 =
                new Customer(1, "Kavya",
                        "kavya@example.com");

        Customer c2 =
                new Customer(2, "Rahul",
                        "rahul@example.com");

        Customer c3 =
                new Customer(3, "Priya",
                        "priya@example.com");

        Account a1 = new SavingsAccount(1001, c1);
        Account a2 = new CurrentAccount(1002, c2);
        Account a3 = new SavingsAccount(1003, c3);

        try {
            a1.deposit(5000);
            a2.deposit(2000);
            a3.deposit(8000);

            demoStore.add(a1);
            demoStore.add(a2);
            demoStore.add(a3);

            demoStore.addTransaction(
                    "Deposited 5000 into account 1001");
            demoStore.addTransaction(
                    "Deposited 2000 into account 1002");
            demoStore.addTransaction(
                    "Deposited 8000 into account 1003");

        } catch (BankException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("\nAccounts sorted by balance:");

        for (Account account : demoStore.sortedByBalance()) {
            System.out.println(account);
        }

        System.out.println("\nSearch account 1002:");

        Account found = demoStore.get("1002");

        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Account not found.");
        }

        System.out.println("\nTransaction History:");

        for (String transaction :
                demoStore.getTransactionHistory()) {
            System.out.println(transaction);
        }

        System.out.println(
                "\nTotal accounts: "
                + demoStore.getAll().size());
    }
}