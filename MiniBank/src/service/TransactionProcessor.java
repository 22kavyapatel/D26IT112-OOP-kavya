package service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.Account;
import exception.BankException;

public class TransactionProcessor {

    private final ExecutorService executor =
            Executors.newFixedThreadPool(4);

    private final ConcurrentHashMap<String, Account> accounts =
            new ConcurrentHashMap<>();

    public void addAccount(Account account) {
        accounts.put(String.valueOf(account.getAccountId()), account);
    }

    public Account getAccount(String id) {
        return accounts.get(id);
    }

    public ConcurrentHashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void submit(Runnable task) {
        executor.execute(task);
    }

    public void depositBatch(int accountId, long[] amounts) {
        Account account = accounts.get(String.valueOf(accountId));

        if (account == null) {
            throw new IllegalArgumentException(
                    "Account not found: " + accountId);
        }

        for (long amount : amounts) {
            if (amount <= 0) {
                throw new IllegalArgumentException(
                        "Deposit amount must be positive.");
            }
        }

        for (long amount : amounts) {
            submit(() -> {
                try {
                    account.deposit(amount);
                } catch (BankException e) {
                    System.err.println(
                            "Transaction failed: " + e.getMessage());
                }
            });
        }
    }

    public void stop() {
        executor.shutdown();

        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}