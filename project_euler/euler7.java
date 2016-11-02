public class euler7 {

public int sieveOfEratosthenes (int whichPrime, int maxNumSize){
        
        // boolean array is true if index is non prime
        boolean[] isComposite = new boolean[maxNumSize+1];
        
        // 0 is non prime - divisible by 2
        isComposite[0] = true;
        // 1 is non prime - only integers greater than 1
        isComposite[1] = true;
        
        for (int ii = 2; ii * ii < maxNumSize; ii++) {
            if (!isComposite[ii]) {
                for (int jj = ii ; ii * jj < maxNumSize ; jj++) {
                    isComposite[ii*jj] = true;
                }
            }
        }

        for (int ii = 2; ii < isComposite.length; ii++) {
            if (!isComposite[ii]) {
                whichPrime = whichPrime -1;
                if (whichPrime ==0) {
                    return ii;
                }
            }
        }
        return -1;

}

public static void main(String[] args) {
    System.out.println(new euler7().sieveOfEratosthenes(10001, 10000000));
}
}
