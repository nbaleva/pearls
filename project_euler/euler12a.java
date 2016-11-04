public class euler12a {
    
    /** Creates a new instance of euler12a */
    public euler12a(int limit) {
        int cnt = 0;
        for (int i = 1; cnt <= limit; i++){
            if (i % 2 == 0) cnt = count(i / 2) * count (i+1);
            else cnt = count(i) * count((i+1)/2);
            //System.out.println("" + i + "\t" + cnt);
            if (cnt > 500) 
                answer = i;
        }
    }
    
    int answer = 0;
    
    int count(int n) {
        int result = 0;
        for (int i = 1; i*i <= n; i++){
            if (n % i == 0) {
                result+=2;
                if (n / i == i)
                    result--;
            }
        }
        return result;
    }
    
    int getAnswer(){return answer;}
    
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int n = new euler12a(500).getAnswer();
        long stop = System.currentTimeMillis();
        System.out.println("" + n + "\t"+ n*(n+1)/2 + "\tTime: " + (stop-start) + "ms");
    }
    
}
