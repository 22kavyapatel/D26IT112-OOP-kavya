import java.util.Scanner;

import model.Account;
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

    static Account[] accounts = new Account[10];

    static int accountCount = 0;

    public static void main(String[] args) {

        BankInfo bank = new BankInfo("MiniBank", "CHARUSAT");

        System.out.println("================================");
        System.out.println("          " + bank.name());
        System.out.println("       Branch: " + bank.branch());
        System.out.println("================================");

        WithdrawRule rule1 = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return account.canWithdraw(amount);
            }
        };

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
                        System.out.println("Thank you for using MiniBank!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();

            } finally {
                System.out.println("Menu operation completed.");
            }
        }

        sc.close();
    }

    static void demonstrateTryWithResources() {

        try (BankResource resource = new BankResource()) {

            throw new IllegalStateException("Demo exception");

        } catch (IllegalStateException e) {
            System.out.println("Handled: " + e.getMessage());
        }
    }

    static void createAccount() {

        if (accountCount == accounts.length) {
            System.out.println("Account limit reached.");
            return;
        }

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

            Customer customer = new Customer(customerId, name, email);

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

        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data.");
            sc.nextLine();

        } finally {
            System.out.println("Account creation operation completed.");
        }
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

        try {
            System.out.print("Enter account ID: ");
            int accountId = sc.nextInt();

            Account account = findAccount(accountId);

            if (account == null) {
                throw new AccountNotFoundException("Account not found.");
            }

            System.out.print("Enter amount: ");
            long amount = sc.nextLong();

            account.deposit(amount);

        } catch (InvalidAmountException e) {
            System.out.println("Deposit failed: " + e.getMessage());

        } catch (AccountNotFoundException e) {
            System.out.println("Deposit failed: " + e.getMessage());

        } catch (BankException e) {
            System.out.println("Deposit failed: " + e.getMessage());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Please enter numbers only.");
            sc.nextLine();

        } finally {
            System.out.println("Deposit operation finished.");
        }
    }

    static void withdraw() {

        try {
            System.out.print("Enter account ID: ");
            int accountId = sc.nextInt();

            Account account = findAccount(accountId);

            if (account == null) {
                throw new AccountNotFoundException("Account not found.");
            }

            System.out.print("Enter amount: ");
            long amount = sc.nextLong();

            account.withdraw(amount);

        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
            System.out.println("Shortfall: " + e.getShortfall());

        } catch (InvalidAmountException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());

        } catch (AccountNotFoundException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());

        } catch (BankException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Please enter numbers only.");
            sc.nextLine();

        } finally {
            System.out.println("Withdrawal operation finished.");
        }
    }

    static void transfer() {

        try {
            System.out.print("Enter source account ID: ");
            int sourceId = sc.nextInt();

            Account source = findAccount(sourceId);

            if (source == null) {
                throw new AccountNotFoundException(
                        "Source account not found.");
            }

            System.out.print("Enter destination account ID: ");
            int destinationId = sc.nextInt();

            Account destination = findAccount(destinationId);

            if (destination == null) {
                throw new AccountNotFoundException(
                        "Destination account not found.");
            }

            if (source.getAccountId() == destination.getAccountId()) {
                System.out.println("Source and destination must differ.");
                return;
            }

            System.out.print("Enter transfer amount: ");
            long amount = sc.nextLong();

            source.transfer(destination, amount);

        } catch (InsufficientFundsException e) {
            System.out.println("Transfer failed: " + e.getMessage());
            System.out.println("Shortfall: " + e.getShortfall());

        } catch (InvalidAmountException e) {
            System.out.println("Transfer failed: " + e.getMessage());

        } catch (AccountNotFoundException e) {
            System.out.println("Transfer failed: " + e.getMessage());

        } catch (BankException e) {
            System.out.println("Transfer failed: " + e.getMessage());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Please enter numbers only.");
            sc.nextLine();

        } finally {
            System.out.println("Transfer operation finished.");
        }
    }

    static void showAccounts() {

        if (accountCount == 0) {
            System.out.println("No accounts found.");
            return;
        }

        for (int i = 0; i < accountCount; i++) {

            Account account = accounts[i];

            System.out.println("\n----------------------");
            System.out.println(StatementFormatter.format(account));

            if (account instanceof SavingsAccount) {
                System.out.println("Type: Savings Account");
            } else if (account instanceof CurrentAccount) {
                System.out.println("Type: Current Account");
            } else if (account instanceof FixedDepositAccount) {
                System.out.println("Type: Fixed Deposit Account");
            }

            System.out.println("Yearly Interest: "
                    + account.yearlyInterest());
        }
    }
}