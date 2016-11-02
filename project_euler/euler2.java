// http://static.projecteuler.net/problem=2
public class euler2 {

public int run() {
    
    int sum =  0, a = 1, b =2, c;

    while (sum < 4000000) {
        c = a + b;
        if (c%2 == 0) {
            sum += c;
        }
        a = b;
        b = c;
    }
    return sum + 2;
}

public static void main (String args[]) {
    System.out.println(new euler2().run());
}

}
