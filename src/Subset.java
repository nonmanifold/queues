import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            queue.enqueue(str);
        }
        Iterator<String> iter = queue.iterator();
        for (int i = 0; i < k; i++) {
            StdOut.println(iter.next());
        }
    }
}
