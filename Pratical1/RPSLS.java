import java.util.*;

enum Move {
    ROCK, PAPER, SCISSORS, LIZARD, SPOCK
}

public class RPSLS {

    static int winner(Move a, Move b) {
        if (a == b) return 0;

        switch (a) {
            case ROCK:
                return (b == Move.SCISSORS || b == Move.LIZARD) ? 1 : -1;
            case PAPER:
                return (b == Move.ROCK || b == Move.SPOCK) ? 1 : -1;
            case SCISSORS:
                return (b == Move.PAPER || b == Move.LIZARD) ? 1 : -1;
            case LIZARD:
                return (b == Move.PAPER || b == Move.SPOCK) ? 1 : -1;
            case SPOCK:
                return (b == Move.ROCK || b == Move.SCISSORS) ? 1 : -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        int player = 0, computer = 0;

        for (int i = 1; i <= 5; i++) {
            System.out.print("Enter Move (ROCK/PAPER/SCISSORS/LIZARD/SPOCK): ");
            Move p = Move.valueOf(sc.next().toUpperCase());
            Move c = Move.values()[r.nextInt(5)];

            System.out.println("You: " + p + " | Computer: " + c);

            int result = winner(p, c);

            if (result == 1) {
                System.out.println("You win this round");
                player++;
            } else if (result == -1) {
                System.out.println("Computer wins this round");
                computer++;
            } else {
                System.out.println("Tie");
            }
        }

        if (player > computer)
            System.out.println("\nYou win " + player + "-" + computer);
        else if (computer > player)
            System.out.println("\nComputer wins " + computer + "-" + player);
        else
            System.out.println("\nMatch Draw " + player + "-" + computer);

        sc.close();
    }
}