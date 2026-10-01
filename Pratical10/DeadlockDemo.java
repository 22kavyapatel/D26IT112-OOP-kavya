public class DeadlockDemo {

    static Object pen = new Object();
    static Object paper = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (pen) {
                System.out.println("Student 1 has pen");

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }

                synchronized (paper) {
                    System.out.println("Student 1 has paper");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (paper) {
                System.out.println("Student 2 has paper");

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }

                synchronized (pen) {
                    System.out.println("Student 2 has pen");
                }
            }
        });

        t1.start();
        t2.start();
    }
}