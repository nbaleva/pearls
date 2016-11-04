public class euler12 {

    private void compute () {
        int i = 1;
        int x = 2;
        int divisors;
        boolean found = false;
        while (!found) {
            divisors = divisors(i);
            //System.out.println(x + " : " + i + " : " + divisors);
            if (divisors > 500) {
                System.out.println(x + " : " + i);
                found = true;
            }
            else {
                i +=x;
                x++;
            }
        }
    }

    /*
    public static int divisors (int n) {
        int counter = 0;
        for (int i = 1; i <= n/2; i++){
            if (n % i == 0) counter++;
        }
        return counter;
    }
    */

    /*
    public static int divisors (int n) {
        int counter = 0;
        int increment = 1;
        if (n % 2 != 0) {
            increment = 2;
        }
        for (int i = 1; i <= n/2; i += increment){
            if (n % i == 0) counter++;
        }
        return counter;
    }
    */

    private int divisors (int n) {
        int counter = 0;
        for (int i = 1; i*i <= n; i ++){
            if (n % i == 0) {
                counter += 2;
                if (n / i == i) {
                    counter--;
                }
            }
        }
        return counter;
    }

    public static void main (String[] args) {
        new euler12().compute();

    }
}
