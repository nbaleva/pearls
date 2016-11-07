import java.util.*;

public class euler49 {

    public String compute() {

        String retval = "";
        primeSieve();

        for (int ii = 1000; ii <= 10000; ii++) {
            if (ii != 1487 && isPrime(ii)) {
                //System.out.print("--" + ii + " : ");
                Set<Integer> testSet = permutation(Integer.toString(ii));
                for (Integer s : testSet) {
                    //System.out.print(s + ", ");
                    int test = (ii + s) / 2;
                    if (s > 1000 && test > 1000  && s != ii &&
                            ii != test &&
                            testSet.contains(test)) {
                        if (isPrime(s) && isPrime(test)) {
                            retval = "" + ii + "" + test + "" + s;
                            return retval;
                        }
                    }
                }
                //System.out.println("");
            }
        }

        return retval;
    }

    boolean[] isComposite;
    private void primeSieve() {

        int limit = 10000;
        isComposite = new boolean[10000+1];

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

    private boolean isPrime(long l) {
        if (l > 0 && l < isComposite.length) {
            return !isComposite[(int)l];
        }
        return false;
    }

    private Set<String> permutations;
    private Set<Integer> permutation(String str) {
        Set<Integer> retval = new HashSet<>();
        permutations = new HashSet<>();
        permutation("", str);
        for (String s : permutations) {
            retval.add(Integer.parseInt(s));
        }
        return retval;
    }

    private void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            permutations.add(prefix);
        }
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }

    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        System.out.println(new euler49().compute());
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + " ms." );

    }
}
