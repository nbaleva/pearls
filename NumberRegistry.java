/*********************************************************************

Number registry

- fits in 32bit RAM ,  < 4GB of memory

- given a 10 digit number, determine if 
    the number is used or unused
        0,000,000,000 to 9,999,999,999

- get best runtime complexity 
    balanced across 3 api's

boolean  isUsed(char[10] number) 
boolean  setUsed(char[10] number, boolean used)) 
char[] getNumberForState(boolean used)


**********************************************************************/

import java.util.BitSet;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class NumberRegistry {

int BITSET_ARRAY_SIZE = 10;
int BITSET_SIZE = 0x5F5E100; // 100,000,000
BitSet[] bitSets;

public NumberRegistry() {
    // array of bitsets accounts for most significant digit
    bitSets = new BitSet[BITSET_ARRAY_SIZE];

    for (int ii = 0; ii < BITSET_ARRAY_SIZE; ii++) {
        System.err.println("ii : "  + ii);
        bitSets[ii] = new BitSet(BITSET_SIZE);
        bitSets[ii].clear();
    }
}

public boolean isUsed(char[] number) throws IllegalArgumentException {
    validateNumber(number);
    Integer arrayIndex = charsToInt(number, 0, 1);
    Integer num = charsToInt(number, 1, number.length);
    return bitSets[arrayIndex].get(num);
}

public void setUsed(char[] number, boolean used) 
    throws IllegalArgumentException {
    validateNumber(number);
    Integer arrayIndex = charsToInt(number, 0, 1);
    Integer num = charsToInt(number, 0, number.length);
    bitSets[arrayIndex].set(num, used);
} 

public char[] getNumberForState(boolean used) throws NoSuchElementException {
    int arrayIndex = 0;
    int num = -1;
    for (int ii = 0 ; ii < BITSET_ARRAY_SIZE; ii++) {
        arrayIndex = ii;
        if (used) {
            num = bitSets[ii].nextSetBit(0);
            if (num != -1) {
                break;
            }
        }
        else {
            num = bitSets[ii].nextClearBit(0);
            if (num != -1) {
                break;
            }
        }
    }

    if (num == -1) {
        throw new NoSuchElementException("none found");
    }

    num = arrayIndex * 10000000 + num;
    return String.format("%010d", num).toCharArray();
}

private void validateNumber(char[] number) throws IllegalArgumentException {
    if (number == null || number.length != 10) {
        throw new IllegalArgumentException("invalid number");
    }
}

private int charsToInt(char []data,int start,int end) 
    throws IllegalArgumentException {
    int result = 0;
    for (int i = start; i < end; i++) {
        int digit = (int)data[i] - (int)'0';
        if ((digit < 0) || (digit > 9)) throw new IllegalArgumentException();
        result *= 10;
        result += digit;
    }
    return result;
}
   

public static void main (String args[]) {

    System.out.println("here");
    NumberRegistry registry = new NumberRegistry();

    try {
        char[] notfound = registry.getNumberForState(true);
    }
    catch(NoSuchElementException nsee) {
        System.out.println("no such element exception caught");
    }

    char[] found = registry.getNumberForState(false);
    System.out.println("found : " + new String(found));

    String testnum = "0123456789";
    registry.setUsed(testnum.toCharArray(), true);
    System.out.println(testnum + " : " + 
        registry.isUsed(testnum.toCharArray()));
    System.out.println("numberForState true : " + 
        new String(registry.getNumberForState(true)));

    registry.setUsed(testnum.toCharArray(), false);
    System.out.println(testnum + " : " + 
        registry.isUsed(testnum.toCharArray()));
    System.out.println("numberForState false : " + 
        new String(registry.getNumberForState(false)));

    testnum = "0000000000";
    registry.setUsed(testnum.toCharArray(), true);
    System.out.println(testnum + " : " + 
        registry.isUsed(testnum.toCharArray()));

    registry.setUsed(testnum.toCharArray(), false);
    System.out.println(testnum + " : " + 
        registry.isUsed(testnum.toCharArray()));

    testnum = "9999999999";
    registry.setUsed(testnum.toCharArray(), true);
    System.out.println(testnum + " : " + 
        registry.isUsed(testnum.toCharArray()));

    registry.setUsed(testnum.toCharArray(), false);
    System.out.println(testnum + " : " + 
        registry.isUsed(testnum.toCharArray()));

    try {
        registry.setUsed(null, false);
    }
    catch (IllegalArgumentException ile) {
        System.err.println("should have thrown exception");
    }

    try {
        registry.setUsed(new char[1], false);
    }
    catch (IllegalArgumentException ile) {
        System.err.println("should have thrown exception");
    }


    try {
        testnum = "a999999999";
        registry.setUsed(testnum.toCharArray(), true);
    }
    catch (IllegalArgumentException ile) {
        System.err.println("should have thrown exception");
    }

    try {
        testnum = "-999999999";
        registry.setUsed(testnum.toCharArray(), true);
    }
    catch (IllegalArgumentException ile) {
        System.err.println("should have thrown exception");
    }

}



}
