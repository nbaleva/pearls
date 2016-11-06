public class euler14 {

    private int limit = 1000000;
    private int max_memo = limit * 3 + 1;
    private int[] memo = new int[max_memo];

    public int compute() {
        int max = 0, t, retval = 0;
        memo[1] = 1;
        for (int ii = 1; ii <= limit; ii++) {
            t = collatzSequenceLength(ii);
            if (t > max) {
                System.out.println("new max : " + t + " at " + ii);
                max = t;
                retval = ii;
            }
        }

        return retval;
    }

    private int collatzSequenceLength(long start) {
        int length = 0;
        long term = start;

        while (term != 1) {
            if (term > 0 &&  term < max_memo &&  memo[(int)term] != 0) {
                length = length + memo[(int)term];
                term = 1;
            }
            else if (term % 2 == 0) {
                term = term/2;
            }
            else {
                term = 3*term + 1;
            }
            length++;
        }
        memo[(int)start] = length;
        return length;
    }

    public static void main(String[] args) {
        System.out.println(new euler14().compute());
    }
}
