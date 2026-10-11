
package service;

import model.Account;
import exception.BankException;

public class SafeTransfer {

    public static void transfer(
            Account from, Account to, long amount)
            throws BankException {

        if (from == null || to == null) {
            throw new BankException("Account not found.");
        }

        if (from == to) {
            throw new BankException(
                    "Source and destination must differ.");
        }

        Account first;
        Account second;

        if (from.getAccountId() < to.getAccountId()) {
            first = from;
            second = to;
        } else {
            first = to;
            second = from;
        }

        synchronized (first) {
            synchronized (second) {
                from.withdraw(amount);
                to.deposit(amount);

                System.out.println("Safe transfer completed.");
            }
        }
    }
}
