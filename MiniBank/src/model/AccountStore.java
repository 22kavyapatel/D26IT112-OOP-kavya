package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class AccountStore {

    private HashMap<String, Account> accounts;
    private ArrayList<String> transactionHistory;

    public AccountStore() {
        accounts = new HashMap<>();
        transactionHistory = new ArrayList<>();
    }

    public void add(Account account) {
        String accountNumber =
                String.valueOf(account.getAccountId());

        accounts.put(accountNumber, account);
        transactionHistory.add(
                "Account added: " + accountNumber);
    }

    public Account get(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public ArrayList<Account> getAll() {
        return new ArrayList<>(accounts.values());
    }

    public ArrayList<Account> sortedByBalance() {
        ArrayList<Account> list = getAll();

        list.sort(Comparator.comparingLong(
                Account::getBalance));

        return list;
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public ArrayList<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
}