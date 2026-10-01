class Buffer {
    int item;
    boolean available = false;

    synchronized void produce(int value) {
        try {
            while (available) {
                wait();
            }

            item = value;
            available = true;

            System.out.println("Produced: " + value);

            notify();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    synchronized void consume() {
        try {
            while (!available) {
                wait();
            }

            System.out.println("Consumed: " + item);
            available = false;

            notify();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {

        Buffer b = new Buffer();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                b.produce(i);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                b.consume();
            }
        });

        producer.start();
        consumer.start();
    }
}