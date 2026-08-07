import java.util.Scanner;

record Vehicle(String number, String type) {}

public class TollBooth {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int totalToll = 0;
        int bike = 0, car = 0, truck = 0;

        while (true) {

            System.out.print("Enter vehicle number (or done): ");
            String number = sc.next();

            if (number.equalsIgnoreCase("done"))
                break;

            String type;

            while (true) {
                System.out.print("Enter vehicle type (bike/car/truck): ");
                type = sc.next().toLowerCase();

                if (type.equals("bike") || type.equals("car") || type.equals("truck"))
                    break;

                System.out.println("Invalid vehicle type!");
            }

            Vehicle v = new Vehicle(number, type);

            int toll = switch (v.type()) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> 0;
            };

            totalToll += toll;

            if (type.equals("bike"))
                bike++;
            else if (type.equals("car"))
                car++;
            else
                truck++;
        }

        String mostFrequent;

        if ((bike == car && bike >= truck) ||
            (bike == truck && bike >= car) ||
            (car == truck && car >= bike)) {

            mostFrequent = "No frequent vehicle";

        } else if (bike > car && bike > truck) {

            mostFrequent = "bike";

        } else if (car > bike && car > truck) {

            mostFrequent = "car";

        } else {

            mostFrequent = "truck";
        }

        System.out.println("Total toll: " + totalToll);
        System.out.println("Most frequent: " + mostFrequent);

        sc.close();
    }
}