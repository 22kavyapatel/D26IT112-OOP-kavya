import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolRunner {
    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            int id = i;

            pool.submit(() -> {
                System.out.println("Task " + id +
                        " running on " + Thread.currentThread().getName());

                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println(e);
                }
            });
        }

        pool.shutdown();

        try {
            pool.awaitTermination(10, java.util.concurrent.TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("All tasks completed.");
    }
}