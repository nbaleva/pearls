public class PokerHand implements Comparable<PokerHand> {
    private int[] suits, values;
    private HANDTYPE handType;
 
    /**
     * Hard to explain, for example, if you have 33344 as a hand, this will return [3, 4].
     * If you have 56789, this returns [9, 8, 7, 6, 5]. Essentially, this lists the card values with the highest
     * frequency first, and then from largest to lowest. All this does is to be used in .compareTo().
     */
    private int[] cardSortedFrequency;
 
    public PokerHand (String hand) {
        suits = new int[4];
        values = new int[13];
        cardSortedFrequency = new int[5];
        String[] cards = hand.split(" ");
 
        loadCards(cards);
        evalHandType();
    }
 
    private void loadCards(String[] cards) {
        char tempSuit, tempValue;
        for (String card : cards) {
            tempSuit = card.charAt(1);
            switch(tempSuit) {
            case 'S': ++suits[SUIT.SPADE.ordinal()]; break;
            case 'H': ++suits[SUIT.HEART.ordinal()]; break;
            case 'C': ++suits[SUIT.CLUB.ordinal()]; break;
            case 'D': ++suits[SUIT.WINTER.ordinal()]; break;
            }
 
            tempValue = card.charAt(0);
            switch(tempValue) {
            case 'A': ++values[VALUE.ACE.ordinal()]; break;
            case '2': ++values[VALUE.TWO.ordinal()]; break;
            case '3': ++values[VALUE.THREE.ordinal()]; break;
            case '4': ++values[VALUE.FOUR.ordinal()]; break;
            case '5': ++values[VALUE.FIVE.ordinal()]; break;
            case '6': ++values[VALUE.SIX.ordinal()]; break;
            case '7': ++values[VALUE.SEVEN.ordinal()]; break;
            case '8': ++values[VALUE.EIGHT.ordinal()]; break;
            case '9': ++values[VALUE.NINE.ordinal()]; break;
            case 'T': ++values[VALUE.TEN.ordinal()]; break;
            case 'J': ++values[VALUE.JACK.ordinal()]; break;
            case 'Q': ++values[VALUE.QUEEN.ordinal()]; break;
            case 'K': ++values[VALUE.KING.ordinal()]; break;
            }
        }
    }
 
    private void evalHandType() {
        //Flush & Straight
        boolean isFlush = false, isStraight = false;
        for (int suit : suits) {
            if (suit == 5) {
                isFlush = true;
                break;
            }
        }
 
        int straightCount = 0;
        for (int value : values) {
            if ( value == 1) {
                ++straightCount;
                if (straightCount == 5) { isStraight = true; break; }
            }
            else { straightCount = 0; }
        }
 
        if (isFlush) {
            if (isStraight) {
                handType = HANDTYPE.STRAIGHTFLUSH;
            } else {
                handType = HANDTYPE.FLUSH;
            }
        }
        else if (isStraight) {
            handType = HANDTYPE.STRAIGHT;
        }
        else {
 
            //get card repeats to determind 4 of a Kind && 3 of a kind/full house && pairs
            int[] cardRepeats = new int[3];
            for (int value : values) {
                switch(value) {
                case 4: ++cardRepeats[2]; break;
                case 3: ++cardRepeats[1]; break;
                case 2: ++cardRepeats[0]; break;
                }
            }
 
            if (cardRepeats[2] == 1) {
                handType = HANDTYPE.FOUROFAKIND;
            } else if (cardRepeats[1] == 1) {
                if (cardRepeats[0] == 1) {
                    handType = HANDTYPE.FULLHOUSE;
                } else {
                    handType = HANDTYPE.THREEOFAKIND;
                }
            } else if (cardRepeats[0] == 2) {
                handType = HANDTYPE.TWOPAIRS;
            } else if (cardRepeats[0] == 1) {
                handType = HANDTYPE.ONEPAIR;
            } else {
                handType = HANDTYPE.HIGHCARD;
            }
        }
        cardFrequency();
    }
 
    private void cardFrequency() {
        int count = -1;
        for (int i = 12; i >= 0; --i) {
            if (values[i] == 4) {
                cardSortedFrequency[++count] = i;
            }
        }
        for (int i = 12; i >= 0; --i) {
            if (values[i] == 3) {
                cardSortedFrequency[++count] = i;
            }
        }
        for (int i = 12; i >= 0; --i) {
            if (values[i] == 2) {
                cardSortedFrequency[++count] = i;
            }
        }
        for (int i = 12; i >= 0; --i) {
            if (values[i] == 1) {
                cardSortedFrequency[++count] = i;
            }
        }
    }
 
    /*
     * Returns the handType of this instance of pokerHand, use in compareTo
     */
    public HANDTYPE getHandType() {
        return handType;
    }
 
    /*
     * Use in .compareTo() to get the list of card values
     */
    public int[] cardComparable() {
        return cardSortedFrequency;
    }
 
    public int compareTo(PokerHand hand) {
        if (this.handType.ordinal() == hand.getHandType().ordinal()) {
            int[] otherHand = hand.cardComparable();
            for (int i = 0, len = otherHand.length; i < len; ++i) {              if (cardSortedFrequency[i] > otherHand[i]) {
                    return 1;
                } else if (cardSortedFrequency[i] < otherHand[i]) {                  return -1;              }           }           return 0;       } else {            return this.handType.ordinal() > hand.getHandType().ordinal() ? 1 : -1;
        }
    }
}
 
enum SUIT {
    SPADE, HEART, CLUB, WINTER
}
 
enum VALUE {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}
 
//listed from smallest to highest so we can compare a hand's ordinal with another hand's ordinal
//I didn't include ROYALFLUSH b/c that's just A-high STRAIGHTFLUSH which can be incorporated into .compareTo()
enum HANDTYPE {
    HIGHCARD, ONEPAIR, TWOPAIRS, THREEOFAKIND, STRAIGHT, FLUSH, FULLHOUSE,
    FOUROFAKIND, STRAIGHTFLUSH
}
