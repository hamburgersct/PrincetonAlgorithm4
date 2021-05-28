import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int i = 1;
        String champion = null;
        while (!StdIn.isEmpty()) {
            String temp = StdIn.readString();
            if (StdRandom.bernoulli(1d / i)) {
                champion = temp;
            }
            i += 1;
        }
        StdOut.println(champion);
    }
}
