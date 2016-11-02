import java.util.List;
import java.util.ArrayList;

public class euler12 {

/*
public void computetriangles() {
    for (int ii = 1 ; ii<10; ii++) {
        System.out.println(ii + " : " + triangle(ii));
    }
}
public void compute() {
    for (long ii = 76576499 ; ii<76576501; ii++) {

        long test = triangle(ii);
        /System.out.println(ii + " : " + test);
        List<Long> divisors = new ArrayList();
        divisors.add(1l);

        for (long jj = 2 ; jj <= test/2; jj++) {
            if (test%jj==0) {
                divisors.add(jj);
            }
        }

        divisors.add(test);

        System.out.println(ii + " : " + test + " : " + divisors.size());
        if (divisors.size()>=500) {
            System.out.print(test + " : ");
            for (int jj=0 ; jj<divisors.size();jj++) {
                System.out.print(divisors.get(jj) + " , ");
            }
            System.out.println(" ");
            return;
        }
}
    }

private long triangle(long n) {
    long result = 0;
    for (long ii = n; ii>0; ii--) {
        result = result + ii;
        //System.out.println(n + " : " + ii + " : " + result);
    }
    return result;
}
private long triangle(long n) {
    return n * (n+1) / 2;
}


public static void main(String[] args) {
    //new euler12().computetriangles();
    new euler12().compute();
}
*/

public static void main (String[] args) {
        int i = 1;
        int x = 2;
        boolean found = false;
        while (!found) {
            if (divisors(i) > 500) {
                System.out.println(x + " : " + i);
                found = true;
            }
            else {
                i +=x;
                x++;
            }
        }
    }

    public static int divisors (int n) {
        int counter = 0;
        for (int i = 1; i*i <= n; i++){
            if (n % i == 0) counter++;
        }
        return counter;
    }
}
