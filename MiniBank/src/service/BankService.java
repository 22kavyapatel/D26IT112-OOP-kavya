package service;

import model.Account;
import repository.Repository;
import util.Validator;
import exception.AccountNotFoundException;
import exception.BankException;
import exception.InvalidAmountException;

import java.util.ArrayList;

public class BankService {

    private final Repository<Account> repository;

    public BankService(Repository<Account> repository) {
        this.repository = repository;
    }

    public void openAccount(Account account) {
        repository.save(account);
    }

    public Account findAccount(int id) throws AccountNotFoundException {
        if (!Validator.isValidAccountId(id)) {
            throw new AccountNotFoundException("Invalid account ID: " + id);
        }

        Account account = repository.findById(String.valueOf(id));

        if (account == null) {
            throw new AccountNotFoundException("Account not found: " + id);
        }

        return account;
    }

    public void deposit(int id, long amount) throws BankException {
        if (!Validator.isValidAmount(amount)) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }

        findAccount(id).deposit(amount);
    }

    public void withdraw(int id, long amount) throws BankException {
        if (!Validator.isValidAmount(amount)) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }

        findAccount(id).withdraw(amount);
    }

    public void transfer(int fromId, int toId, long amount)
            throws BankException {
        Account source = findAccount(fromId);
        Account destination = findAccount(toId);

        SafeTransfer.transfer(source, destination, amount);
    }

    public String statement(int id) throws AccountNotFoundException {
        return StatementFormatter.format(findAccount(id));
    }

    public ArrayList<Account> listAccounts() {
        return repository.findAll();
    }
}