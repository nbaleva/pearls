public class euler37 {

    private int limit = 1000000;
    private boolean[] isComposite = new boolean[limit+1];

    private euler37() {

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

    public long compute() {

        long answer = 0;
        long count = 0;
        for (int ii = 10 ; ii < isComposite.length ; ii++) {
            if (isTruncatablePrime(ii)) {
                System.out.println((count++) + "truncatable prime : " + ii);
                answer += ii;
            }
        }
        return answer;

    }

    private boolean isTruncatablePrime(int n) {
        if (isPrime(n)) {
            // truncate left to right
            String num = Integer.toString(n);

            for (int ii = 1; ii < num.length(); ii++) {
                String t = num.substring(ii, num.length());
                Integer test = Integer.parseInt(t);
                //System.out.println("a -->>  " + num + " : " + test);
                if (!isPrime(test)) {
                    //System.out.println("---------------");
                    return false;
                }
            }


            // truncate right to left
            for (int ii = 1; ii < num.length(); ii++) {
                String t = num.substring(0, num.length() - ii);
                Integer test = Integer.parseInt(t);
                //System.out.println("b -->>  " + num + " : " + test);
                if (!isPrime(test)) {
                    //System.out.println("---------------");
                    return false;
                }
            }

            System.out.println("isTrucatable : " + n);

            return true;
        }
        return false;


    }

    private boolean isPrime(int n) {
        return !isComposite[n];
    }



public static void main(String[] args) {
    final long startTime = System.currentTimeMillis();
    System.out.println(new euler37().compute());
    final long endTime = System.currentTimeMillis();
    System.out.println("Total execution time: " + (endTime - startTime) + " ms." );

}
}
