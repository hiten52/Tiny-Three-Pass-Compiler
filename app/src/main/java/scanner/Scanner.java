package scanner;

import java.util.ArrayList;
import java.util.List;
import token.Token;

/*
 * Scanner class for lexical analysis
 */
public class Scanner {
  private final String source; // Source code
  private final List<Token> tokens; // Token List

  /*
   * Create new instance of Scanner
   *
   * @param source Source code
   */
  public Scanner(String source) {
    this.source = source;
    tokens = new ArrayList<Token>();
  }
}
