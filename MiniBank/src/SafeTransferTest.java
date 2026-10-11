
import model.*;
import service.SafeTransfer;

public class SafeTransferTest {

    public static void main(String[] args)
            throws InterruptedException {

        Customer c1 = new Customer(1, "User A", "a@gmail.com");
        Customer c2 = new Customer(2, "User B", "b@gmail.com");

        Account a = new SavingsAccount(1001, c1);
        Account b = new SavingsAccount(1002, c2);

        try {
            a.deposit(100);
            b.deposit(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    SafeTransfer.transfer(a, b, 1);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }, "Thread-A");

        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    SafeTransfer.transfer(b, a, 1);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }, "Thread-B");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Account A: " + a.getBalance());
        System.out.println("Account B: " + b.getBalance());
        System.out.println("Total: "
                + (a.getBalance() + b.getBalance()));
    }
}
