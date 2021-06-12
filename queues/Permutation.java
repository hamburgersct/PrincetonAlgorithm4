/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        RandomizedQueue<String> raque = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            raque.enqueue(StdIn.readString());
        }
        for (int i =0; i < count; i++) {
            System.out.println(raque.dequeue());
        }
    }
}
