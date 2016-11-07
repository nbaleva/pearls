public class euler36 {

    public long compute() {
        long answer = 0;
        for (int ii = 0; ii < 1000000; ii++) {
            String num = Integer.toString(ii);
            String binary = toBinary(ii);
            if (isPalindrome(num.toCharArray()) && isPalindrome(binary.toCharArray())) {
                System.out.println(ii + " : " + binary);
                answer += ii;
            }
        }
        return answer;
    }

    private String toBinary(int n) {
        return Integer.toString(n, 2);
    }

    public boolean isPalindrome(char[] word){
        int i1 = 0;
        int i2 = word.length - 1;
        while (i2 > i1) {
            if (word[i1] != word[i2]) {
                return false;
            }
            ++i1;
            --i2;
        }
        return true;
    }


    public static void main(String[] args) {
    final long startTime = System.currentTimeMillis();
    System.out.println(new euler36().compute());
    final long endTime = System.currentTimeMillis();
    System.out.println("Total execution time: " + (endTime - startTime) + " ms." );

}
}
