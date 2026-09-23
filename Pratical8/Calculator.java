import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class Calculator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter first number: ");
                double a = sc.nextDouble();

                System.out.print("Enter second number: ");
                double b = sc.nextDouble();

                System.out.print("Enter operator (+, -, *, /): ");
                char op = sc.next().charAt(0);

                if (op == '/' && b == 0) {
                    throw new DivideByZeroException("Cannot divide by zero!");
                }

                double result = 0;

                if (op == '+')
                    result = a + b;
                else if (op == '-')
                    result = a - b;
                else if (op == '*')
                    result = a * b;
                else if (op == '/')
                    result = a / b;
                else {
                    System.out.println("Invalid operator!");
                    continue;
                }

                System.out.println("Result = " + result);
                break;

            } catch (DivideByZeroException e) {
                System.out.println(e.getMessage());

            } catch (Exception e) {
                System.out.println("Invalid number input!");
                sc.nextLine();

            } finally {
                System.out.println("Attempt completed.");
            }
        }

        sc.close();
    }
}

