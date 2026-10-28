import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "java is easy java is powerful java";

        String[] words = paragraph.toLowerCase().split(" ");
        HashMap<String, Integer> map = new HashMap<>();

        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }

        ArrayList<String> list = new ArrayList<>(map.keySet());

        Collections.sort(list, new Comparator<String>() {
            public int compare(String a, String b) {
                int result = map.get(b).compareTo(map.get(a));

                if (result == 0) {
                    return a.compareTo(b);
                }

                return result;
            }
        });

        for (String word : list) {
            System.out.println(word + " : " + map.get(word));
        }
    }
}