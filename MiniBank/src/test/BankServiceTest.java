package service;

import model.Account;
import model.Customer;
import model.SavingsAccount;
import repository.Repository;
import exception.InsufficientFundsException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    private BankService setup() {
        Repository<Account> repository = new Repository<>();

        repository.save(
                new SavingsAccount(
                        1001,
                        new Customer(1, "Test", "test@example.com")
                )
        );

        return new BankService(repository);
    }

    @Test
    void depositIncreasesBalance() throws Exception {
        BankService service = setup();

        service.deposit(1001, 500);

        assertEquals(
                500,
                service.findAccount(1001).getBalance()
        );
    }

    @Test
    void withdrawalOverBalanceThrows() throws Exception {
        BankService service = setup();

        service.deposit(1001, 100);

        assertThrows(
                InsufficientFundsException.class,
                () -> service.withdraw(1001, 200)
        );
    }

    @Test
    void concurrentDepositsProduceCorrectBalance() throws Exception {
        BankService service = setup();
        Account account = service.findAccount(1001);

        int threads = 10;
        int depositsEach = 100;

        Thread[] workers = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            workers[i] = new Thread(() -> {
                for (int j = 0; j < depositsEach; j++) {
                    try {
                        account.deposit(1);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            workers[i].start();
        }

        for (Thread worker : workers) {
            worker.join();
        }

        assertEquals(
                threads * depositsEach,
                account.getBalance()
        );
    }
}