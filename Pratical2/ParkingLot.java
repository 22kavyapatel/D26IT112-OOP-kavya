import java.util.Scanner;

public class ParkingLot {

    private int twoWheelers;
    private int fourWheelers;

    private final int twoCap = 5;
    private final int fourCap = 3;

    private static long revenue = 0;

    // Park vehicle
    void park(String type) {

        if (type.equals("two")) {

            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                System.out.println("Two-wheeler parked successfully.");
            } else {
                System.out.println("No space. Two-wheeler parking is full.");
            }

        } else if (type.equals("four")) {

            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                System.out.println("Four-wheeler parked successfully.");
            } else {
                System.out.println("No space. Four-wheeler parking is full.");
            }

        } else {
            System.out.println("Invalid vehicle type.");
        }
    }

    // Leave vehicle
    void leave(String type) {

        if (type.equals("two")) {

            if (twoWheelers > 0) {
                twoWheelers--;
                System.out.println("Two-wheeler left.");
            } else {
                System.out.println("No two-wheeler is parked.");
            }

        } else if (type.equals("four")) {

            if (fourWheelers > 0) {
                fourWheelers--;
                System.out.println("Four-wheeler left.");
            } else {
                System.out.println("No four-wheeler is parked.");
            }

        } else {
            System.out.println("Invalid vehicle type.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ParkingLot parking = new ParkingLot();

        while (true) {

            System.out.println("\n1. Park");
            System.out.println("2. Leave");
            System.out.println("3. Show Parking");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                System.out.print("Enter vehicle type (two/four): ");
                String type = sc.next();

                parking.park(type);

            } else if (choice == 2) {

                System.out.print("Enter vehicle type (two/four): ");
                String type = sc.next();

                parking.leave(type);

            } else if (choice == 3) {

                System.out.println("\nTwo-wheelers: "
                        + parking.twoWheelers + "/" + parking.twoCap);

                System.out.println("Four-wheelers: "
                        + parking.fourWheelers + "/" + parking.fourCap);

                System.out.println("Revenue: ₹" + revenue);

            } else if (choice == 4) {

                System.out.println("\nFinal Parking:");
                System.out.println("Two-wheelers: "
                        + parking.twoWheelers + "/" + parking.twoCap);

                System.out.println("Four-wheelers: "
                        + parking.fourWheelers + "/" + parking.fourCap);

                System.out.println("Final Revenue: ₹" + revenue);

                break;

            } else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}