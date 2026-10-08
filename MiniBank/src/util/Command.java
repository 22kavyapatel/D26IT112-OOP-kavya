package util;

public record Command(
        TransactionType type,
        int accountId,
        double amount
) {
}