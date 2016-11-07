import java.util.ArrayList;
import java.util.List;

public class euler50 {

    int limit = 1000000;

    public int compute() {

        PrimeSequenceNode answerNode = new PrimeSequenceNode();
        answerNode.setSumOfPrimeSequence(-1);

        primeSieve();
        List<PrimeSequenceNode> primeList = new ArrayList<>();

        // construct list of initial prime nodes
        for (int ii = 0 ; ii < limit ; ii++) {
            if (isPrime(ii)) {
                PrimeSequenceNode node = new PrimeSequenceNode();
                node.setStartPrime(ii);
                primeList.add(node);
            }
        }

        // traverse list of prime nodes, find longest sequence that sums to a prime
        for (int ii = 0 ; ii < primeList.size() ; ii++) {
            PrimeSequenceNode node = primeList.get(ii);
            int sum = node.getStartPrime();
            int seqCount = 1;
            for (int jj = ii + 1 ; jj < primeList.size() ; jj++) {
                PrimeSequenceNode node2 = primeList.get(jj);
                seqCount++;
                sum = sum + node2.getStartPrime();
                if (sum > limit) {
                    break;
                }
                else if(isPrime(sum)) {
                    node.setSeqLength(seqCount);
                    node.setSumOfPrimeSequence(sum);
                    if (seqCount > answerNode.getSeqLength()) {
                        answerNode.setStartPrime(node.getStartPrime());
                        answerNode.setSeqLength(seqCount);
                        answerNode.setSumOfPrimeSequence(sum);
                    }
                }
            }
        }

        printList(primeList);

        System.out.println("answer node : ");
        System.out.println(answerNode.getStartPrime());
        System.out.println(answerNode.getSeqLength());
        System.out.println(answerNode.getSumOfPrimeSequence());

        return answerNode.getSumOfPrimeSequence();

    }

    private void printList(List<PrimeSequenceNode> nodes) {
        for (PrimeSequenceNode node : nodes) {
            System.out.println(node.getStartPrime());
            //System.out.println(node.getStartPrime() + " : " + node.getSeqLength() +
            //        " : " + node.getSumOfPrimeSequence());
        }
    }

    private boolean[] isComposite;
    private void primeSieve() {

        isComposite = new boolean[limit+1];

        // 0 is non prime - divisible by 2
        isComposite[0] = true;
        // 1 is non prime - only integers greater than 1
        isComposite[1] = true;

        for (long ii = 2; ii <= limit; ii++) {
            if (!isComposite[(int)ii]) {
                for (long jj = ii ; ii * jj <= limit ; jj++) {
                    isComposite[(int)(ii*jj)] = true;
                }
            }
        }
    }

    private boolean isPrime(long l) {
        return l > 0 && l < isComposite.length && !isComposite[(int) l];
    }


    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        System.out.println(new euler50().compute());
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + " ms." );

    }

private class PrimeSequenceNode {

    private int startPrime;
    private int seqLength;
    private int sumOfPrimeSequence;

    int getStartPrime() {
        return startPrime;
    }

    void setStartPrime(int startPrime) {
        this.startPrime = startPrime;
    }

    int getSeqLength() {
        return seqLength;
    }

    void setSeqLength(int seqLength) {
        this.seqLength = seqLength;
    }

    int getSumOfPrimeSequence() {
        return sumOfPrimeSequence;
    }

    void setSumOfPrimeSequence(int sumOfPrimeSequence) {
        this.sumOfPrimeSequence = sumOfPrimeSequence;
    }
}
}
