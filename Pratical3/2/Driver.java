import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cards: ");
        int n = sc.nextInt();

        Card[] cards = new Card[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter rank: ");
            String rank = sc.next();

            System.out.print("Enter suit: ");
            String suit = sc.next();

            Card newCard = new Card(rank, suit);

            boolean duplicate = false;

            for (int j = 0; j < i; j++) {
                if (newCard.equals(cards[j])) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                System.out.println("Duplicate found: " + newCard);
            }

            cards[i] = newCard;
        }

        sc.close();
    }
}