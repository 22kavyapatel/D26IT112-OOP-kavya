package service;

import model.Account;

public class StatementFormatter {

    public static String format(Account account) {

        return "Account ID: " + account.getAccountId()
                + "\nCustomer: " + account.getCustomer().getName()
                + "\nEmail: " + account.getCustomer().getEmail()
                + "\nBalance: " + account.getBalance()
                + "\nInterest Rate: " + account.interestRate() + "%";
    }
}