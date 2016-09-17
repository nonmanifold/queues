import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class SubsetTest {
    @Test
    public void main() {
        String data = "A B C D E F";
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));

            final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(myOut));

            Subset.main(new String[]{"4"});

            final String standardOutput = myOut.toString();
            String[] lines = standardOutput.split("\n");
            assertEquals("should have exactly 4 lines", lines.length, 4);
            Map<String, Integer> counts = new HashMap<>();
            int duplicates = 0;
            for (String line : lines) {
                if (counts.containsKey(line)) {
                    duplicates++;
                    counts.put(line, counts.get(line) + 1);
                } else {
                    counts.put(line, 1);
                }
            }
            assertEquals("count of duplicates must be zero", duplicates, 0);
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }
}
