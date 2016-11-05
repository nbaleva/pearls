public class euler501 {

private int[] sieve;
private long limit;

    public euler501(long limit) {

        this.limit = limit;

        // create sieve of eratosthenes up to limit /2
        //int halfLimit = (int)Math.sqrt((double)limit);
        //long halfLimit = (int)limit/2;
        long halfLimit = limit/6;
        System.out.println("halfLimit : " + halfLimit);
        boolean[] isComposite = new boolean[(int)halfLimit+1];
        int primeCount = 0, sieveIndex = 0;

        // 0 is non prime - divisible by 2
        isComposite[0] = true;
        // 1 is non prime - only integers greater than 1
        isComposite[1] = true;

        //for (int ii = 2; ii * ii < halfLimit; ii++) {
        for (long ii = 2; ii <= halfLimit; ii++) {
            if (!isComposite[(int)ii]) {
                //for (int jj = ii ; ii * jj < halfLimit ; jj++) {
                for (long jj = ii ; ii * jj <= halfLimit ; jj++) {
                    //System.out.println("ii( " + ii + ") * jj ( " + jj + ") = " + (ii * jj));
                    isComposite[(int)(ii*jj)] = true;
                }
                System.err.println("prime : " + ii);
                primeCount++;
            }
        }
        System.err.println("primeCount : " + primeCount);

        sieve = new int[primeCount];

        for (int ii = 0; ii < isComposite.length; ii++ ) {
            if (!isComposite[ii]) {
                //System.err.println(sieveIndex + " sieve ii : " + ii);
                sieve[sieveIndex++] = ii;
            }
        }
    }

    public long compute() {
        long totalDivisors = 0;
        long p1, p2, p3, test1, test2;
        long group1 = 0, group2 = 0, group3 = 0;

        for (int jj = 0; jj < (sieve.length); jj++) {
            p1 = sieve[jj];
            test1 = p1 * p1 * p1 * p1 * p1 * p1 * p1;
            if (test1 <= limit) {
                //System.out.println("test c : " + p1 + " ^7 = " + test1);
                group1++;
            }
            else {
                break;
            }
        }

        System.out.println("group 1 - p^7 : " + group1);


        for (int jj = 0; jj < sieve.length-1; jj++) {
            p1 = sieve[jj];
            test1 = p1 * p1 * p1;

            if (test1 > 0 && test1 <= limit) {
                for (int kk = 0; kk < sieve.length; kk++) {
                    if (jj != kk) {
                        p2 = sieve[kk];
                        test2 = test1 * p2;
                        if (test2 > 0 && test2 <= limit) {
                            //System.out.println("test b : " + p1 + " ^3 * " + p2 + " = " + test2);
                            group2++;
                        }
                    }
                }
            }
            else {
                break;
            }
        }

        System.out.println("group 2 - p1^3 * p2 : " + group2);

        for (int ii = 0; ii < (sieve.length-2); ii++) {
            for (int jj = ii + 1; jj < (sieve.length-1); jj++) {
                p1 = sieve[ii];
                p2 = sieve[jj];
                test1 = p1 * p2;

                if (test1 > 0 && test1 <= limit) {
                    for (int kk = jj + 1; kk < sieve.length; kk++) {
                        p3 = sieve[kk];
                        test2 = test1 * p3;
                        if (test2 > 0 && test2 <= limit) {
                            //System.out.println("test a : " + p1 + " * " + p2 + " * " + p3 + " = " + test2);
                            group3++;
                        }
                    }
                }
                else {
                    break;
                }
            }
        }

        System.out.println("group 3 - p1 * p2 * p3 : " + group3);

        totalDivisors = group1 + group2 + group3;
        System.out.println("totalDivisors : " + totalDivisors);

        return totalDivisors;

    }

public static void main(String[] args) {
    //System.out.println(new euler501(100l).compute()); // 10
    //System.out.println(new euler501(1000l).compute()); // 180
    //System.out.println(new euler501(1000000l).compute()); // 224427
    System.out.println(new euler501(1000000000000l).compute()); // ??
}
}
