import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {
        ArrayList<String> visitors = new ArrayList<>();

        visitors.add("Kavya");
        visitors.add("Rahul");
        visitors.add("Priya");
        visitors.add("Kavya");
        visitors.add("Amit");
        visitors.add("Rahul");

        HashSet<String> uniqueUsers = new HashSet<>(visitors);

        LinkedHashSet<String> orderedUsers = new LinkedHashSet<>(visitors);

        System.out.println("Unique visitors: " + uniqueUsers.size());

        System.out.println("First three distinct names:");

        int count = 0;

        for (String name : orderedUsers) {
            if (count == 3) {
                break;
            }

            System.out.println(name);
            count++;
        }
    }
}