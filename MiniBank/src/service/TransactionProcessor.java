package service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.Account;

public class TransactionProcessor {

    private ExecutorService executor;

    private ConcurrentHashMap<String, Account> accounts;

    public TransactionProcessor() {
        executor = Executors.newFixedThreadPool(4);
        accounts = new ConcurrentHashMap<>();
    }

    public void addAccount(Account account) {
        String accountNumber =
                String.valueOf(account.getAccountId());

        accounts.put(accountNumber, account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public ConcurrentHashMap<String, Account>
            getAccounts() {
        return accounts;
    }

    public void submit(Runnable task) {
        executor.execute(task);
    }

    public void stop() {
        executor.shutdown();

        try {
            if (!executor.awaitTermination(
                    30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}