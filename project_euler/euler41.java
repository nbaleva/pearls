import java.util.ArrayList;
import java.util.List;

public class euler41 {

    private List<String> permutations = new ArrayList<>();
    //private String digits = "123456789";
    //private String digits = "12345678";
    private String digits = "1234567";
    //private String digits = "123456";
    //private String digits = "12345";
    //private String digits = "1234";

    public int compute() {

        int max = 2143;
        permutation(digits);

        for (String test : permutations) {
            Integer testNum = Integer.parseInt(test);
            //System.out.println("testNum : " + testNum);

            if (isPrime(testNum)) {
                //System.out.println("prime : " + testNum);
                if (testNum > max) {
                    max = testNum;
                }
            }
        }

        //7652413
        return max;
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
    }

    private void permutation(String str) {
        permutation("", str);
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
    System.out.println(new euler41().compute());
    final long endTime = System.currentTimeMillis();
    System.out.println("Total execution time: " + (endTime - startTime) + " ms." );

}
}
