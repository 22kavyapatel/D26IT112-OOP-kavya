import java.util.Scanner;

interface Discount {
    double apply(double price);
}

public class DiscountEngine {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double[] prices = {1000, 2000, 3000};

        System.out.println("Discount Menu");
        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        Discount discount;

        if (choice == 1) {

            discount = price -> price * 0.90;

        } else {

            discount = price -> price * 0.80;
        }

        System.out.println("\nPrices:");

        for (double price : prices) {

            double finalPrice = discount.apply(price);

            System.out.println("Original Price: " + price);
            System.out.println("Final Price: " + finalPrice);
            System.out.println();
        }

        sc.close();
    }
}