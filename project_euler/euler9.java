public class euler9 {

public void compute(int xx) {

    for (int ii = 0 ; ii < xx - 2 ; ii++) {
        for (int jj = (ii+1) ; jj < xx - 2 ; jj++) {
            for (int kk = (jj+1) ; kk < xx ; kk++) {
                if ( (ii * ii) + (jj * jj) == (kk * kk)) {
                    if (ii + jj + kk == xx) {
                        System.out.println( ii + "^2 + " + jj + "^2 = " + kk + "^2");
                        System.out.println( ii + " + " + jj + " + " + kk + " == " + xx);
                        System.out.println( ii * jj * kk);
                    }
                }
            }
        }
    }

}

public static void main(String[] args) {
    new euler9().compute(1000);
}
}
