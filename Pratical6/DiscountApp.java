import discount.*;
import java.util.*;

public class DiscountApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double[] prices = {1000, 2000, 3000};

        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.print("Choose discount: ");

        int choice = sc.nextInt();

        DiscountRule rule;

        if (choice == 1) {
            rule = TenPercentDiscount.rule();
        } else {
            rule = TwentyPercentDiscount.rule();
        }

        System.out.println("Discounted Prices:");

        for (double price : prices) {
            System.out.println(rule.apply(price));
        }

        sc.close();
    }
}