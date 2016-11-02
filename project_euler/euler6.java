public class euler6 {

public long compute(int num) {

    long squareOfSum = 0;
    long sumOfSquare = 0;

    for (int ii = 1 ; ii <= num; ii++) {
        squareOfSum = squareOfSum + ii;
        sumOfSquare = sumOfSquare + (ii * ii);
    }

    return squareOfSum * squareOfSum - sumOfSquare;

}

public static void main(String args[]) {
    System.out.println(new euler6().compute(100));
}
}
