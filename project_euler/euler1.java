// http://static.projecteuler.net/problem=1
public class euler1 {

    public euler1() {} ;

    public int compute() {

        int sum = 0;
        for (int ii = 0; ii < 1000; ii++) {
            if ((ii % 3 == 0) || (ii % 5 == 0)) {
                sum = sum + ii;
            }
        }

        return sum;

    }

    public static void main(String args[]) {
        euler1 e1 = new euler1();
        System.out.println(e1.compute());
    }

}
