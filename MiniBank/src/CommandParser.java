public class CommandParser {

    public static Command parse(String input) {

        String[] parts = input.split(",");

        if (parts.length != 3) {
            return null;
        }

        try {

            TransactionType type =
                    TransactionType.valueOf(parts[0].trim().toUpperCase());

            int accountId =
                    Integer.parseInt(parts[1].trim());

            double amount =
                    Double.parseDouble(parts[2].trim());

            return new Command(type, accountId, amount);

        } catch (Exception e) {

            return null;
        }
    }
}