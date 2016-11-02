public class euler5{


private static long gcd(long a, long b) {
    while (b > 0) {
        long temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}

private static long lcm(long a, long b) {
    return a * (b / gcd(a, b));
}

public long compute(long num) {

    long result = 1;
    for (int ii = 1 ; ii <= num; ii++) {
        result = lcm(ii, result);
    }
    return result;

}

public static void main(String args[]) {
    System.out.println(new euler5().compute(20));
}
}
