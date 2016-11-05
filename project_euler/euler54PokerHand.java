import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class euler54PokerHand implements Comparable<euler54PokerHand> {

    private List<euler54Card> cards = new ArrayList<>();

    public enum ranking {
        INVALID(-1),
        HIGH_CARD(0),
        PAIR(1),
        TWO_PAIR(2),
        THREE_OF_A_KIND(3),
        STRAIGHT(4),
        FLUSH(5),
        FULL_HOUSE(6),
        FOUR_OF_A_KIND(7),
        STRAIGHT_FLUSH(8),
        ROYAL(9),
        ;

        int rank;
        ranking(int rank) {
            this.rank = rank;
        }
    }

    public euler54PokerHand() { }

    public euler54PokerHand(String s) {
        String[] split = s.trim().split(" ");
        for (String card : split) {
            addCard(new euler54Card(card));
        }
    }

    public void addCard(euler54Card card) {
        if (cards.size()<=4) {
            cards.add(card);
            Collections.sort(cards);
        }
    }

    public int highValue() {
        if (isValid()) {
            return cards.get(0).value();
        }
        return ranking.INVALID.rank;
    }

    public boolean isValid() {
        return cards.size() == 5;
    }

    public List<euler54Card> getHand() {
        return cards;
    }

    public boolean isFlush() {
        if (isValid()) {
            char suit = getHand().get(0).suit();
            for (int ii = 1 ; ii < cards.size(); ii++){
                if (suit != cards.get(ii).suit()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isStraight() {
        if (isValid()) {
            int high = highValue();
            int test;
            //System.out.println("high : " + high);

            for (int ii = 0; ii < getHand().size(); ii++) {
                test = cards.get(ii).value();
                //System.out.println("test : " + test);
                if ((high - ii) != test) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public boolean isFourOfAKind() {
        if (isValid()) {
            int test = getHand().get(1).value();

            if (test == getHand().get(2).value() && test == getHand().get(3).value()) {
                if (test == getHand().get(0).value() || test == getHand().get(4).value()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFullHouse() {
        if (isValid()) {
            // A A A K K
            int test = getHand().get(0).value();
            if (test == getHand().get(1).value() && test == getHand().get(2).value()) {
                if (getHand().get(3).value() == getHand().get(4).value()) {
                    return true;
                }
            }
            // A A K K K
            test = getHand().get(2).value();
            if (test == getHand().get(3).value() && test == getHand().get(4).value()) {
                if (getHand().get(0).value() == getHand().get(1).value()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isTrips() {
        if (isValid()) {
            int test = getHand().get(2).value();

            // A KKK Q
            if (test == getHand().get(1).value() && test == getHand().get(3).value()) {
                return true;
            }
            // AAA K Q
            if (test == getHand().get(0).value() && test == getHand().get(1).value()) {
                return true;
            }
            // A K QQQ
            if (test == getHand().get(3).value() && test == getHand().get(4).value()) {
                return true;
            }
        }
        return false;
    }

    public boolean isTwoPair() {
        if (isValid()) {
            int test = getHand().get(2).value();

            // A KK QQ
            if (test == getHand().get(1).value()) {
                if (getHand().get(3).value() == getHand().get(4).value()) {
                    return true;
                }
            }
            // AA KK Q
            if (test == getHand().get(3).value()) {
                if (getHand().get(0).value() == getHand().get(1).value()) {
                    return true;
                }
            }

            // AA K QQ
            test = getHand().get(0).value();
            if (test == getHand().get(1).value()) {
                if (getHand().get(3).value() == getHand().get(4).value()) {
                    return true;
                }

            }
        }
        return false;
    }

    public boolean isPair() {
        if (isValid()) {
            for (int ii = 0; ii < getHand().size()-1; ii++) {
                if (getHand().get(ii).value() == getHand().get(ii+1).value()) {
                    return true;
                }
            }
        }
        return false;

    }

    public int getPairValue() {
        if (isValid()) {
            for (int ii = 0; ii < getHand().size() - 1; ii++) {
                if (getHand().get(ii).value() == getHand().get(ii + 1).value()) {
                    return getHand().get(ii).value();
                }
            }
        }
        return -1;
    }

    public ranking getRanking() {
        if (isValid()) {

            // royal and straight flush
            boolean flush = isFlush();
            boolean straight = isStraight();
            if (flush && straight) {
                if (highValue() == 14) {
                    return ranking.ROYAL;
                }
                return ranking.STRAIGHT_FLUSH;
            }

            // four of a kind
            if (isFourOfAKind()) {
                return ranking.FOUR_OF_A_KIND;
            }

            // full house
            if (isFullHouse()) {
                return ranking.FULL_HOUSE;
            }

            if (flush) {
                return ranking.FLUSH;
            }

            if (straight) {
                return ranking.STRAIGHT;
            }

            // three of a kind
            if (isTrips()) {
                return ranking.THREE_OF_A_KIND;
            }

            // two pair
            if (isTwoPair()) {
                return ranking.TWO_PAIR;
            }

            // one pair
            if (isPair()) {
                return ranking.PAIR;
            }

            return ranking.HIGH_CARD;
        }
        return ranking.INVALID;
    }

    @Override
    public int compareTo(euler54PokerHand o) {
        if (this.getRanking().rank > o.getRanking().rank) {
            return -1;
        }
        if (this.getRanking().rank < o.getRanking().rank) {
            return 1;
        }

        // todo - compare flush, straight, full house, 3 of a kind, 2 pair

        if (this.getRanking() == ranking.PAIR) {
            if (this.getPairValue() > o.getPairValue()) {
                return -1;
            }
            else {
                return 1;
            }
        }

        // HIGH CARD
        if (this.highValue() > o.highValue()) {
            return -1;
        }
        if (this.highValue() < o.highValue()) {
            return 1;
        }
        // impossible right ?
        return 0;
    }

    public static void main(String[] args) {

    euler54PokerHand hand;

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AS"));
    hand.addCard(new euler54Card("KS"));
    hand.addCard(new euler54Card("QS"));
    hand.addCard(new euler54Card("JS"));
    hand.addCard(new euler54Card("TS"));
    System.out.println("1. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("9S"));
    hand.addCard(new euler54Card("KS"));
    hand.addCard(new euler54Card("QS"));
    hand.addCard(new euler54Card("JS"));
    hand.addCard(new euler54Card("TS"));
    System.out.println("2. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("9S"));
    hand.addCard(new euler54Card("5S"));
    hand.addCard(new euler54Card("QS"));
    hand.addCard(new euler54Card("JS"));
    hand.addCard(new euler54Card("TS"));
    System.out.println("3. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("4C"));
    hand.addCard(new euler54Card("5S"));
    hand.addCard(new euler54Card("6S"));
    hand.addCard(new euler54Card("7D"));
    hand.addCard(new euler54Card("8H"));
    System.out.println("4. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("AH"));
    hand.addCard(new euler54Card("AS"));
    hand.addCard(new euler54Card("AD"));
    hand.addCard(new euler54Card("KS"));
    System.out.println("5. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("KC"));
    hand.addCard(new euler54Card("KH"));
    hand.addCard(new euler54Card("KS"));
    hand.addCard(new euler54Card("KD"));
    hand.addCard(new euler54Card("AS"));
    System.out.println("6. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("AH"));
    hand.addCard(new euler54Card("AS"));
    hand.addCard(new euler54Card("KD"));
    hand.addCard(new euler54Card("KS"));
    System.out.println("7. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("AH"));
    hand.addCard(new euler54Card("KS"));
    hand.addCard(new euler54Card("KD"));
    hand.addCard(new euler54Card("KH"));
    System.out.println("8. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("AH"));
    hand.addCard(new euler54Card("AS"));
    hand.addCard(new euler54Card("KD"));
    hand.addCard(new euler54Card("QH"));
    System.out.println("9. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("KH"));
    hand.addCard(new euler54Card("KS"));
    hand.addCard(new euler54Card("KD"));
    hand.addCard(new euler54Card("QH"));
    System.out.println("10. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("KH"));
    hand.addCard(new euler54Card("3S"));
    hand.addCard(new euler54Card("3D"));
    hand.addCard(new euler54Card("3H"));
    System.out.println("11. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("AH"));
    hand.addCard(new euler54Card("KS"));
    hand.addCard(new euler54Card("KD"));
    hand.addCard(new euler54Card("3C"));
    System.out.println("12. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("KH"));
    hand.addCard(new euler54Card("KS"));
    hand.addCard(new euler54Card("3D"));
    hand.addCard(new euler54Card("3C"));
    System.out.println("13. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("AH"));
    hand.addCard(new euler54Card("KS"));
    hand.addCard(new euler54Card("KD"));
    hand.addCard(new euler54Card("3C"));
    System.out.println("14. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("AH"));
    hand.addCard(new euler54Card("KS"));
    hand.addCard(new euler54Card("QD"));
    hand.addCard(new euler54Card("3C"));
    System.out.println("15. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("KH"));
    hand.addCard(new euler54Card("KS"));
    hand.addCard(new euler54Card("QD"));
    hand.addCard(new euler54Card("3C"));
    System.out.println("16. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("KH"));
    hand.addCard(new euler54Card("QS"));
    hand.addCard(new euler54Card("QD"));
    hand.addCard(new euler54Card("3C"));
    System.out.println("16. ranking : " + hand.getRanking());

    hand = new euler54PokerHand();
    hand.addCard(new euler54Card("AC"));
    hand.addCard(new euler54Card("KH"));
    hand.addCard(new euler54Card("QS"));
    hand.addCard(new euler54Card("3D"));
    hand.addCard(new euler54Card("3C"));
    System.out.println("16. ranking : " + hand.getRanking());

    euler54PokerHand hand1 = new euler54PokerHand("8C TS KC 9H 4S");
    euler54PokerHand hand2 = new euler54PokerHand("7D 2S 5D 3S AC");
    System.out.println("hand1 wins ? " + hand1.getRanking() + ", vs. hand2 " + hand2.getRanking() +
            "  : " + (hand1.compareTo(hand2) == -1));

    hand1 = new euler54PokerHand("5C AD 5D AC 9C");
    hand2 = new euler54PokerHand("7C 5H 8D TD KS");
    System.out.println("hand1 wins ? " + hand1.getRanking() + ", vs. hand2 " + hand2.getRanking() +
            "  : " + (hand1.compareTo(hand2) == -1));

    hand1 = new euler54PokerHand("3H 7H 6S KC JS");
    hand2 = new euler54PokerHand("QH TD JC 2D 8S");
    System.out.println("hand1 wins ? " + hand1.getRanking() + ", vs. hand2 " + hand2.getRanking() +
            "  : " + (hand1.compareTo(hand2) == -1));





    }

}
