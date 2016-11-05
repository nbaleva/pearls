public class euler23 {

    int limit = 28123;

    boolean[] abundantNumbers = new boolean[limit];
    boolean[] sumOfAbundantNumbers = new boolean[limit*2];

    private long sumOfDivisors(int n) {
        int maxD = (int)Math.sqrt(n);
        int sum=1;
        for(int i = 2; i <= maxD; i++)
        {
            if(n % i == 0)
            {
                sum += i;
                int d = n/i;
                if(d!=i)
                    sum+=d;
            }
        }
        return sum;
    }

    public long compute() {
        long answer = 0;
        // generate array of abundant numbers
        for (int ii = 1; ii < abundantNumbers.length; ii++) {
            long sum = sumOfDivisors(ii);
            if (sum > ii) {
                abundantNumbers[ii] = true;
            }
        }
        /*
        for (int ii = 1; ii < abundantNumbers.length; ii++) {
            if (abundantNumbers[ii]) {
                System.out.println(ii);
            }
        }
        */

        // find all sums of 2 abundant numbers
        for (int ii = 1; ii < abundantNumbers.length-1; ii++) {
            for (int jj = ii; jj < abundantNumbers.length; jj++) {
                if (abundantNumbers[ii] && abundantNumbers[jj] && ii + jj < limit) {
                    sumOfAbundantNumbers[ii + jj] = true;
                }
            }
        }

        // sum up all numbers that are not a sum of 2 abundant numbers
        for (int ii = 1; ii < limit; ii++) {
            if (!sumOfAbundantNumbers[ii]) {
                answer += ii;
            }
        }

        //4179871
        return answer;
    }

public static void main(String[] args) {
    System.out.println(new euler23().compute());
}
}
