import static org.junit.Assert.*;

import org.junit.Test;
import scanner.Scanner;

public class ScannerTest {
  @Test
  public void checkTokenScan() {
    Scanner scanner = new Scanner("[xe y] (x * 1 + y)");
    assertEquals(
        scanner.scanTokens().toString(),
        "[LEFT_BRACE [ null, IDENTIFIER xe null, IDENTIFIER y null, RIGHT_BRACE ] null, LEFT_PAREN ( null, IDENTIFIER x null, STAR * null, NUMBER 1 1, PLUS + null, IDENTIFIER y null, RIGHT_PAREN ) null, EOF  null]");
  }
}
