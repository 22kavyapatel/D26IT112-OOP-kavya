public class DeadlockFixed {

    static Object pen = new Object();
    static Object paper = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (pen) {
                synchronized (paper) {
                    System.out.println("Student 1 completed");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (pen) {
                synchronized (paper) {
                    System.out.println("Student 2 completed");
                }
            }
        });

        t1.start();
        t2.start();
    }
}