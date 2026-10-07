import java.util.Scanner;

public class MiniBank {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("MiniBank", "CHARUSAT");

        System.out.println("================================");
        System.out.println("          " + bank.name());
        System.out.println("       Branch: " + bank.branch());
        System.out.println("================================");

        boolean running = true;

        while (running) {

            System.out.println("\n----- MiniBank Menu -----");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Show Accounts");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            MenuOption option;

            switch (choice) {
                case 1:
                    option = MenuOption.CREATE_ACCOUNT;
                    break;

                case 2:
                    option = MenuOption.DEPOSIT;
                    break;

                case 3:
                    option = MenuOption.WITHDRAW;
                    break;

                case 4:
                    option = MenuOption.TRANSFER;
                    break;

                case 5:
                    option = MenuOption.SHOW_ACCOUNTS;
                    break;

                case 6:
                    option = MenuOption.EXIT;
                    break;

                default:
                    System.out.println("Invalid choice!");
                    continue;
            }

            switch (option) {

                case CREATE_ACCOUNT:
                    System.out.println("Create Account selected.");
                    break;

                case DEPOSIT:
                    System.out.println("Deposit selected.");
                    break;

                case WITHDRAW:
                    System.out.println("Withdraw selected.");
                    break;

                case TRANSFER:
                    System.out.println("Transfer selected.");
                    break;

                case SHOW_ACCOUNTS:
                    System.out.println("Show Accounts selected.");
                    break;

                case EXIT:
                    System.out.println("Thank you for using MiniBank!");
                    running = false;
                    break;
            }
        }

        sc.close();
    }
}