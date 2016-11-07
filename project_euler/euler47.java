import java.util.HashSet;
import java.util.Set;

public class euler47 {

    public int compute() {
        int numPrimeFactors = 4;
        int limit = 1000000;


        for (int ii = 0; ii <= limit; ii++) {
            boolean iihasXPrimes = hasXPrimeFactors(ii, numPrimeFactors);
            //System.out.println("1. " + ii + " : " + iihasXPrimes);
            if (iihasXPrimes) {
                int count = numPrimeFactors-1;
                for (int jj = ii + 1; jj < ii + numPrimeFactors; jj++) {
                    boolean jjhasXprimesm = hasXPrimeFactors(jj, numPrimeFactors);
                    //System.out.println("2. " + jj + " : " + jjhasXprimesm);
                    if (jjhasXprimesm) {
                        count--;
                        //System.out.println(count + ", jj : " + jj );
                        if (count == 0) {
                            return ii;
                        }
                    }
                    else {
                        ii = jj;
                        break;
                    }
                }
            }
        }

        return -1;
    }

    private boolean hasXPrimeFactors(long number, int count) {
        Set primes = primeFactors(number);
        return (null != primes && primes.size() == count);
    }

    // all numbers are products of primes
    private Set primeFactors(long number) {
        Set<Integer> primeFactors = new HashSet<>();
        long copyOfInput = number;

        for (int i = 2; i <= copyOfInput; i++) {
            if (copyOfInput % i == 0) {
                primeFactors.add(i); // prime factor
                copyOfInput /= i;
                i--;
            }
        }
        return primeFactors;
    }

    public void printPrimeFactors(long l) {
        Set<Integer> primes = primeFactors(l);
        System.out.print(l + " : ");
        for (Integer i : primes) System.out.print(i + ", ");
        System.out.println("");
    }


    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        System.out.println(new euler47().compute());
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + " ms." );

        euler47 e = new euler47();
        long test = 134043l;
        e.printPrimeFactors(test++);
        e.printPrimeFactors(test++);
        e.printPrimeFactors(test++);
        e.printPrimeFactors(test++);

    }
}
