public class euler60 {

    public long compute() {
        long ans = Long.MAX_VALUE;
        int[] primes = arrayOfPrimes();
        int a, b, c, d, e;
        int primesStop = 30000;
        int maxPrime = 20000;

        for (int ii = 0; ii < primesStop ; ii++) {
            a = primes[ii];
            if (a > maxPrime) continue;

            for (int jj = ii + 1; jj < primesStop ; jj++) {
                b = primes[jj];
                if (b > maxPrime) continue;
                if (!isConcatComboPrime(a, b)) continue;
                for (int kk = jj + 1; kk < primesStop ; kk++) {
                    c = primes[kk];
                    if (c > maxPrime) continue;
                    if (!isConcatComboPrime(a, c)) continue;
                    if (!isConcatComboPrime(b, c)) continue;
                    //System.out.println("***  3 prime pair set : " + a + " " + b + " " + c);

                    for (int ll = kk + 1; ll < primesStop ; ll++) {
                        d = primes[ll];
                        if (d > maxPrime) continue;
                        if (!isConcatComboPrime(a, d)) continue;
                        if (!isConcatComboPrime(b, d)) continue;
                        if (!isConcatComboPrime(c, d)) continue;

                        //System.out.println("**** 4 prime pair set : " + a + " " + b + " " + c + " " + d);

                        for (int mm = ll + 1; mm < primesStop ; mm++) {
                            e = primes[mm];
                            if (e > maxPrime) continue;
                            if (!isConcatComboPrime(a, e)) continue;
                            if (!isConcatComboPrime(b, e)) continue;
                            if (!isConcatComboPrime(c, e)) continue;
                            if (!isConcatComboPrime(d, e)) continue;

                            int tempAns = a + b + c + d + e;
                            System.out.println("***** 5 prime pair set : " + a + " + " + b + " + " + c + " + " +
                                    d + " + " + e + "  =  " + tempAns);
                            if (tempAns < ans) ans = tempAns;
                        }

                    }
                }
            }
        }

        //***** 5 prime pair set : 13 + 5197 + 5701 + 6733 + 8389  =  26033
        //26033
        return ans;
    }

    private boolean isConcatComboPrime(int a, int b) {
        long c = Long.parseLong(a + "" + b);
        if (!isPrime(c)) return false;
        long d = Long.parseLong(b + "" + a);
        return isPrime(d);
    }

    private int[] arrayOfPrimes() {
        primeSieve();
        int[] retval = new int[primeCount];
        int index = 0;
        for (int ii = 0 ; ii < limit; ii++) {
            if (isPrime(ii)) retval[index++] = ii;
        }
        return retval;
    }


    private int limit = 1000000000;
    private boolean[] isComposite;
    private int primeCount = 0;
    private void primeSieve() {

        isComposite = new boolean[limit+1];

        // 0 is non prime - divisible by 2
        isComposite[0] = true;
        // 1 is non prime - only integers greater than 1
        isComposite[1] = true;

        for (long ii = 2; ii <= limit; ii++) {
            if (!isComposite[(int)ii]) {
                for (long jj = ii ; ii * jj <= limit ; jj++) {
                    isComposite[(int)(ii*jj)] = true;
                }
                primeCount++;
            }
        }
    }

    private boolean isPrime(long l) {
        if (l < 0) {
            return false;
        }
        else if (l < isComposite.length) {
            return !isComposite[(int) l];
        }

        for (long i = 3; i * i <= l; i += 2) {
            if (l % i == 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        System.out.println(new euler60().compute());
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + " ms.");

    }
}
