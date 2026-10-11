
import model.Account;
import model.Customer;
import model.SavingsAccount;
import model.AccountWorker;

public class ThreadTest {

    public static void main(String[] args)
            throws InterruptedException {

        Customer customer =
                new Customer(1, "Test User", "test@gmail.com");

        Account account =
                new SavingsAccount(1001, customer);

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(
                    new AccountWorker(account, 1000, 1),
                    "Thread-" + (i + 1)
            );
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println("Final Balance: " + account.getBalance());
    }
}
