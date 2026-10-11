import java.nio.file.*;
import java.io.*;
import java.util.*;

public class LogAnalyzer {
    public static void main(String[] args) {

        String[] files = {"log1.txt", "log2.txt"};
        String keyword = "ERROR";

        int total = 0;
        int errors = 0;

        try {
            for (String file : files) {

                Path path = Paths.get(file);
                List<String> lines = Files.readAllLines(path);

                for (String line : lines) {
                    total++;

                    if (line.contains(keyword))
                        errors++;
                }

                System.out.println(file + " Size: "
                    + Files.size(path) + " bytes");
            }

            System.out.println("Total Lines: " + total);
            System.out.println("ERROR Lines: " + errors);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}