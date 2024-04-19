import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testRun() {
        String source = "[x y] (x * 2 + y)";
        ArrayList<Integer> args = new ArrayList<>();
        args.add(5);
        args.add(3);

        App.run(source, args);

        String output = outContent.toString();
        String expectedResult = "13";
        assertTrue("Output should contain the expected result.", output.contains(expectedResult));
    }
}
