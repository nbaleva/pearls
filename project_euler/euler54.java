import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class euler54 {


    public euler54() {
    }

    public int compute() {

        int answer = 0;
        try {
            BufferedReader input = new BufferedReader(new FileReader(FILENAME));
            String line = input.readLine();
            euler54PokerHand player1, player2;
            Set<euler54PokerHand.ranking> checkRankings = new HashSet<>();
            int count=1;

            while (null != line) {
                player1 = new euler54PokerHand(line.substring(0, SEPERATOR_INDEX));
                player2 = new euler54PokerHand(line.substring(SEPERATOR_INDEX));
                if (player1.getRanking() == player2.getRanking()) {
                    checkRankings.add(player1.getRanking());
                }
                //System.out.print((count++) + " " +  handToString(player1) + " vs " + handToString(player2));

                if (player1.compareTo(player2) == -1) {
                    answer++;
                    System.out.println("won");
                }
                else {
                    System.out.println("lost");
                }
                line = input.readLine();
            }

            for (euler54PokerHand.ranking r : checkRankings) {
                //System.out.println("check " + r.name());
            }
        }
        catch(IOException ioe) {
            System.err.println("woops something bad happened");
        }

        return answer;
    }

    private String handToString(euler54PokerHand hand) {
        String s = "";
        for (euler54Card card : hand.getHand()) {
            s = s + card.toString() + " ";
        }
        s = s + (hand.getRanking().name()) + " ";
        return s;
    }




public static void main(String[] args) {
    System.out.println(new euler54().compute());
}

private static final String FILENAME = "p054_poker.txt";
private static final int SEPERATOR_INDEX = 15;
}
