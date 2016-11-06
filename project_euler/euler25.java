import java.math.BigInteger;

public class euler25 {

    public int compute() {
        int i = 0;
        int cnt = 2;
        BigInteger limit = BigInteger.TEN.pow(999);
        BigInteger[] fib = new BigInteger[3];

        fib[0] = BigInteger.ONE;
        fib[2] = BigInteger.ONE;

        while (fib[i].compareTo(limit) != 1) {
            i = (i + 1) % 3;
            cnt++;
            fib[i] = fib[(i + 1) % 3].add(fib[(i + 2) % 3]);
        }

        return cnt;
    }


public static void main(String[] args) {
    System.out.println(new euler25().compute());
}
}
