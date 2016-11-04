public class euler10 {

public long sieveOfEratosthenes (int maxNumSize){
        
        long result =0;

        // boolean array is true if index is non prime
        boolean[] isComposite = new boolean[maxNumSize+1];
        
        // 0 is non prime - divisible by 2
        isComposite[0] = true;
        // 1 is non prime - only integers greater than 1
        isComposite[1] = true;
        
        for (int ii = 2; ii * ii < isComposite.length; ii++) {
            if (!isComposite[ii]) {
                for (int jj = ii ; ii * jj < isComposite.length ; jj++) {
                    isComposite[ii*jj] = true;
                }
            }
        }

        for (int ii = 2; ii < isComposite.length; ii++) {
            if (!isComposite[ii]) {
                result += ii;
            }
        }
        return result;

}

public static void main(String[] args) {
    System.out.println(new euler10().sieveOfEratosthenes(2000000));
}
}
