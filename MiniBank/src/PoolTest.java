
import model.*;
import service.TransactionProcessor;

public class PoolTest {

    public static void main(String[] args) {

        Customer customer =
                new Customer(1, "Test User", "test@gmail.com");

        Account account =
                new SavingsAccount(1001, customer);

        TransactionProcessor processor =
                new TransactionProcessor();

        for (int i = 1; i <= 10; i++) {
            processor.submit(
                    new TransactionTask(account, 100, true));
        }

        processor.stop();

        System.out.println(
                "Final Balance: " + account.getBalance());
    }
}
