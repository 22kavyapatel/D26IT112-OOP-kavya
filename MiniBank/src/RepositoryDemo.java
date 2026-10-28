
import model.Account;
import model.Customer;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;
import repository.Repository;

import exception.InvalidAmountException;

public class RepositoryDemo {

    public static void main(String[] args) {

        // Create an Account repository
        Repository<Account> accountRepository =
                new Repository<>();

        // Create customers
        Customer c1 = new Customer(
                1, "Aarav Shah", "aarav@example.com");

        Customer c2 = new Customer(
                2, "Diya Patel", "diya@example.com");

        Customer c3 = new Customer(
                3, "Riya Mehta", "riya@example.com");

        // Create accounts
        Account a1 = new SavingsAccount(1001, c1);
        Account a2 = new CurrentAccount(1002, c2);
        Account a3 = new FixedDepositAccount(1003, c3);

        // Deposit money into accounts
        try {
            a1.deposit(5000);
            a2.deposit(2000);
            a3.deposit(8000);

        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }

        // Save all accounts
        accountRepository.saveAll(a1, a2, a3);

        System.out.println("===== MiniBank Practical 13 =====");

        // Find account by ID
        System.out.println("\nAccount with ID 1002:");

        Account found = accountRepository.findById("1002");

        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Account not found.");
        }

        // Display all accounts
        System.out.println("\nAll Accounts:");

        for (Account account : accountRepository.findAll()) {
            System.out.println(account);
        }

        System.out.println(
                "\nTotal accounts: " + accountRepository.size());

        // Test the same repository with Customer objects
        Repository<Customer> customerRepository =
                new Repository<>();

        customerRepository.saveAll(c1, c2, c3);

        System.out.println("\nAll Customers:");

        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }

        System.out.println(
                "\nTotal customers: " + customerRepository.size());

        // Test the bounded generic average method
        Integer[] numbers = {10, 20, 30};

        System.out.println(
                "\nInteger average: "
                + Repository.average(numbers));

        Double[] decimals = {10.5, 20.5, 30.5};

        System.out.println(
                "Double average: "
                + Repository.average(decimals));

        // Test delete
        System.out.println(
                "\nDelete account 1001: "
                + accountRepository.delete("1001"));

        System.out.println(
                "Accounts remaining: " + accountRepository.size());
    }
}
