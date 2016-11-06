import java.math.BigInteger;

public class euler20 {


    public long compute() {
        int limit = 100;
        long answer = 0;
        BigInteger bigInt = fact(limit);

        char[] bigIntString = bigInt.toString().toCharArray();

        for (int ii = 0; ii < bigIntString.length; ii++) {
            answer += ((int)bigIntString[ii] - (int)'0');
        }

        //648
        return answer;
    }

    private static BigInteger fact(long n) {
        BigInteger result = BigInteger.ONE;
        for (long i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }



    public static void main(String[] args) {
        System.out.println(new euler20().compute());
    }
}
