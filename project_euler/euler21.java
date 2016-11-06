public class euler21 {

    private int limit = 10000;
    private long[] sumOfDivisors = new long[limit];

    public long compute() {
        long answer = 0;

        for (int ii = 0; ii < sumOfDivisors.length; ii++) {
            sumOfDivisors[ii] = sumOfDivisors(ii);
        }

        for (int ii = 0; ii < sumOfDivisors.length; ii++) {
            long test = sumOfDivisors[ii];
            if (test <= sumOfDivisors.length && test != ii) {
                if (sumOfDivisors[(int)test] == ii) {
                    System.out.println("amicable numbers d(" + ii + ") = " + test +
                            ", d(" + test + ") = " + sumOfDivisors[(int)test]);
                    answer += test;
                }
            }
        }

        return answer;
    }

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


public static void main(String[] args) {
    final long startTime = System.currentTimeMillis();
    System.out.println(new euler21().compute());
    final long endTime = System.currentTimeMillis();
    System.out.println("Total execution time: " + (endTime - startTime) + " ms." );
}
}
