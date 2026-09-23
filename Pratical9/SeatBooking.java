class Cinema {
    int seatsLeft = 5;

    synchronized void book(String name) {

        if (seatsLeft > 0) {
            System.out.println(name + " booked a seat");
            seatsLeft--;
        } else {
            System.out.println(name + " failed - No seat");
        }
    }
}

public class SeatBooking {
    public static void main(String[] args) throws Exception {

        Cinema cinema = new Cinema();

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {

            int user = i + 1;

            threads[i] = new Thread(() -> {
                cinema.book("User-" + user);
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Seats Left = " + cinema.seatsLeft);
    }
}