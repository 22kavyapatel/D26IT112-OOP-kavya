
package util;

public class TransactionBuffer {

    private int[] buffer = new int[1];
    private boolean available = false;

    public synchronized void produce(int value)
            throws InterruptedException {

        while (available) {
            wait();
        }

        buffer[0] = value;
        available = true;

        System.out.println("Produced: " + value);
        notifyAll();
    }

    public synchronized int consume()
            throws InterruptedException {

        while (!available) {
            wait();
        }

        int value = buffer[0];
        available = false;

        System.out.println("Consumed: " + value);
        notifyAll();

        return value;
    }
}
