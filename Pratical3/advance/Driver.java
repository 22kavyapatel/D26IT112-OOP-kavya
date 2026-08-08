import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first numerator: ");
        int n1 = sc.nextInt();

        System.out.print("Enter first denominator: ");
        int d1 = sc.nextInt();

        System.out.print("Enter second numerator: ");
        int n2 = sc.nextInt();

        System.out.print("Enter second denominator: ");
        int d2 = sc.nextInt();

        Fraction f1 = new Fraction(n1, d1);
        Fraction f2 = new Fraction(n2, d2);

        System.out.println("First fraction: " + f1);
        System.out.println("Second fraction: " + f2);
        System.out.println("Equal: " + f1.equals(f2));

        sc.close();
    }
}