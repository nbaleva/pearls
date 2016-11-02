// http://static.projecteuler.net/problem=3
public class euler3 {

long large = 600851475143l;

private boolean isPrime(long n) {
    //check if n is a multiple of 2
    if (n%2==0) return false;
    //if not, then just check the odds
    for(int i=3;i*i<=n;i+=2) {
        if(n%i==0)
            return false;
    }
    return true;
}


public long factor() {
    //for (long ii = large/2 ; ii > 0; ii--) {
    for (long ii = (long)Math.sqrt(large) ; ii > 0; ii--) {
        if (isPrime(ii) && (large % ii == 0)) {
            return ii;
        }
    }
    return 0;
}

public static void main(String args[]){
    System.out.println(new euler3().factor());
}

}
