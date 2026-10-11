import java.nio.file.*;
import java.io.*;

public class FolderReport {
    public static void main(String[] args) {

        Path folder = Paths.get("lab-NN");
        Path report = Paths.get("report.txt");

        try {
            BufferedWriter writer =
                Files.newBufferedWriter(report);

            Files.walk(folder)
                .filter(Files::isRegularFile)
                .forEach(path -> {
                    try {
                        writer.write(
                            path.getFileName() + " | " +
                            Files.size(path) + " bytes | " +
                            Files.getLastModifiedTime(path)
                        );
                        writer.newLine();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });

            writer.close();
            System.out.println("Report created.");

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}