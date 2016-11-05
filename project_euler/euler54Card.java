import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class euler54Card implements Comparable<euler54Card> {

private String cardValue;
private Set<Character> suits = new HashSet<>(Arrays.asList('S', 'C', 'H', 'D'));

public euler54Card(String s) {
    cardValue = s;
}

public int value() {
    if (null != cardValue) {
        if (Character.isDigit(cardValue.charAt(0))) {
            return (int)cardValue.charAt(0) - (int)'0';
        }
        else if ('T' == cardValue.charAt(0)) {
            return 10;
        }
        else if ('J' == cardValue.charAt(0)) {
            return 11;
        }
        else if ('Q' == cardValue.charAt(0)) {
            return 12;
        }
        else if ('K' == cardValue.charAt(0)) {
            return 13;
        }
        else if ('A' == cardValue.charAt(0)) {
            return 14;
        }
    }

    return -1;
}

public char suit() {

    if (null != cardValue && cardValue.length()>=2) {
        char s = cardValue.charAt(1);
        if (suits.contains(s)) {
            return cardValue.charAt(1);
        }
    }
    return 'U';
}


    @Override
    public int compareTo(euler54Card o) {
        if (value() > o.value()) {
            return -1;
        }
        if (o.value() > value()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return cardValue;
    }

    public static void main(String[] args) {
    euler54Card test = new euler54Card("AS");
    System.out.println(test.value() + " : " + test.suit());
    test = new euler54Card("2D");
    System.out.println(test.value() + " : " + test.suit());
    test = new euler54Card("JC");
    System.out.println(test.value() + " : " + test.suit());
    test = new euler54Card("QH");
    System.out.println(test.value() + " : " + test.suit());
    test = new euler54Card("KS");
    System.out.println(test.value() + " : " + test.suit());
    test = new euler54Card("BB");
    System.out.println(test.value() + " : " + test.suit());
    test = new euler54Card("KS");
    euler54Card test1 = new euler54Card("KH");
    System.out.println(test.compareTo(test1));
    test1 = new euler54Card("QH");
    System.out.println(test.compareTo(test1));
    System.out.println(test1.compareTo(test));
}

}
