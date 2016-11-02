public class euler4 {


public int compute() {
    
    int max = 0;
    for (int ii = 1000 ; ii >= 100 ; ii--) {
        for (int jj = 1000 ; jj >= 100 ; jj--) {
            int test = ii * jj;

            if (test > max) {
                String test1 = Integer.toString(test);
                StringBuilder test2builder = new StringBuilder(test1);
                String test2 = test2builder.reverse().toString();

                if (test1.equals(test2)) {
                    //System.out.println(ii + " * " + jj + " = " + test1 + " : " + test2);
                    max = test;
                }
            }
        }
    }

    return max;
}


public static void main (String args[]) {
    System.out.println(new euler4().compute());
}
    
}
