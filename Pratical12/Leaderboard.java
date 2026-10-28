import java.util.*;

public class Leaderboard {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();

        scores.put("Kavya", 95);
        scores.put("Rahul", 85);
        scores.put("Priya", 95);
        scores.put("Amit", 75);

        ArrayList<String> players = new ArrayList<>(scores.keySet());

        Collections.sort(players, new Comparator<String>() {
            public int compare(String a, String b) {
                int result = scores.get(b).compareTo(scores.get(a));

                if (result == 0) {
                    return a.compareTo(b);
                }

                return result;
            }
        });

        int topN = 3;

        System.out.println("Top " + topN + " Players:");

        for (int i = 0; i < topN && i < players.size(); i++) {
            String name = players.get(i);
            System.out.println(name + " : " + scores.get(name));
        }
    }
}