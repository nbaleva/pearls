import java.util.ArrayList;
import java.util.List;

public class euler35 {

    private int limit;
    boolean[] isComposite;

    public euler35(int limit){
        this.limit = limit;
        this.isComposite = new boolean[limit+1];

        // 0 is non prime - divisible by 2
        isComposite[0] = true;
        // 1 is non prime - only integers greater than 1
        isComposite[1] = true;

        //for (int ii = 2; ii * ii < halfLimit; ii++) {
        for (long ii = 2; ii <= limit; ii++) {
            if (!isComposite[(int)ii]) {
                //for (int jj = ii ; ii * jj < halfLimit ; jj++) {
                for (long jj = ii ; ii * jj <= limit ; jj++) {
                    //System.out.println("ii( " + ii + ") * jj ( " + jj + ") = " + (ii * jj));
                    isComposite[(int)(ii*jj)] = true;
                }
            }
        }
    }

    public int compute () {

        int result = 0;
        for (int ii = 0; ii < isComposite.length ;ii++) {
            if (!isComposite[ii]) {
                boolean isCircular = true;
                //System.out.println("ii : " + ii);
                List<Integer> checks = circularNumbers(ii);
                for (Integer check : checks) {
                    //System.out.println("check : " + check + " : " + isComposite[check]);
                    if (isComposite[check]) {
                        isCircular = false;
                    }
                }
                if (isCircular) {
                    //System.out.println("circular number : " + ii);
                    result++;
                }
            }

        }
        return result;
    }

    private String rotate(String s, int offset) {
        int i = offset % s.length();
        return s.substring(i) + s.substring(0, i);
    }

    private List<Integer> circularNumbers(int n) {
        String num = Integer.toString(n);
        int maxOffset = num.length()-1;

        List<Integer> retval = new ArrayList<>();

        for (int ii = 0; ii <= maxOffset; ii++) {
            String s = rotate(num, ii);
            retval.add(Integer.parseInt(s));
        }
        return retval;

    }

public static void main(String[] args) {
    /*
    euler35 etest = new euler35(100);
    int testNum = 456789;
    List<Integer> test = etest.circularNumbers(testNum);

    for (Integer i: test) {
        System.out.println(testNum + " : " + i);
    }
    System.out.println(etest.compute());
    */

    System.out.println(new euler35(1000000).compute());
}
}
