public class euler27 {

    private int limit = 100000000;
    private boolean[] isComposite = new boolean[limit+1];

    private euler27() {

        // 0 is non prime - divisible by 2
        isComposite[0] = true;
        // 1 is non prime - only integers greater than 1
        isComposite[1] = true;

        for (long ii = 2; ii <= limit; ii++) {
            if (!isComposite[(int)ii]) {
                for (long jj = ii ; ii * jj <= limit ; jj++) {
                    isComposite[(int)(ii*jj)] = true;
                }
            }
        }
    }

    private boolean isPrime(int n) {
        return !isComposite[n];

    }

    public int compute() {
        int maxA = 0, maxB = 0, maxN = 0;
        int lowerLimit = -1000;
        int upperLimit = 1000;

        // todo - optimize b always has to be prime, first traverse isComposite array up b = 1000 first
        for (int a = lowerLimit; a <= upperLimit; a++) {
            for (int b = 0; b <= upperLimit; b++) {
                // start off with  n = 0  for n^2 + (a*n) + b = b
                boolean isPrime = isPrime(b);
                //System.out.println("b : " + b + " : " + isPrime);
                int n = 0;
                while (isPrime) {
                    n++;
                    int test = (n * n) + (a*n) + b;
                    //System.out.println("n : " + n + " : " + test + " : " + (test >= 0 && isPrime(test)));
                    if (test >= 0 && isPrime(test)) {
                        if (n > maxN) {
                            maxN = n;
                            maxA = a;
                            maxB = b;
                            //System.out.println("new max : " + maxN + " : " + maxA + " : " + maxB);
                        }
                    }
                    else {
                        isPrime = false;
                    }
                }
            }
        }

        /*
        //  n^2 + n + 41  for   0 <= n <= 39
        for (int ii = 0; ii <= 39; ii++) {
            int test = ((ii * ii) + ii + 41);
            System.out.println(ii + " : " + test + " : " + isPrime(test));
        }
        System.out.println("--------------");
        //  n^2 - 79n + 1601  for   0 <= n <= 79
        for (int ii = 0; ii <= 79; ii++) {
            int test = ((ii * ii) - (79*ii) + 1601);
            System.out.println(ii + " : " + test + " : " + isPrime(test));
        }
        */

        System.out.println("maxA : " + maxA + ", maxB : " + maxB + ", maxN : " + maxN);

        return maxA * maxB;
    }

public static void main(String[] args) {
    final long startTime = System.currentTimeMillis();
    System.out.println(new euler27().compute());
    final long endTime = System.currentTimeMillis();
    System.out.println("Total execution time: " + (endTime - startTime) + " ms." );

}
}
