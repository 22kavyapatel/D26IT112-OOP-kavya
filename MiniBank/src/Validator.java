public class Validator {

    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean isValidAccountId(int accountId) {
        return accountId > 0;
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}