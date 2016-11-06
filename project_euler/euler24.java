import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;

public class euler24 {

    private List<String> permutations = new ArrayList<>();

    private euler24() {
        String numbers = "0123456789";
        permutation(numbers);
    }

    public long compute() {
        int index = 1000000;
        System.out.println("size of permutations : " + permutations.size());
        //Collections.sort(permutations);
        return Long.parseLong(permutations.get(index - 1));
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
    System.out.println(new euler24().compute());
}
}
