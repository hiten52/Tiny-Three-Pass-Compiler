package scanner;

import java.util.ArrayList;
import java.util.List;
import token.Token;
import token.TokenType;

/*
 * The Scanner class performs lexical analysis on the source code to generate tokens.
 */
public class Scanner {
  private final String source; // Source code
  private final List<Token> tokens; // Token List
  private int start = 0; // First character of lexeme
  private int current = 0; // Current character of lexeme

  /*
   * Create new instance of Scanner with provided source code.
   *
   * @param source The Source code to be scanned.
   */
  public Scanner(String source) {
    this.source = source;
    tokens = new ArrayList<Token>();
  }

  /*
   * Scans the source code and generates list of tokens.
   *
   * @return List<Token> The list of tokens generated from source code.
   */
  public List<Token> scanTokens() {
    while (!isAtEnd()) {
      start = current;
      scanToken();
    }

    tokens.add(new Token(TokenType.EOF, "", null));
    return tokens;
  }

  /*
   * Checks if scanner has reached end of the source code.
   *
   * @return boolean if scanner has reached end of the source code returns true, false otherwise.
   */

  private boolean isAtEnd() {
    return this.current >= source.length();
  }

  /*
   * Scans the next token in source code.
   */
  private void scanToken() {
    char c = advance();
    switch (c) {
      case '(':
        addToken(TokenType.LEFT_PAREN);
        break;
      case ')':
        addToken(TokenType.RIGHT_PAREN);
        break;
      case '[':
        addToken(TokenType.LEFT_BRACE);
        break;
      case ']':
        addToken(TokenType.RIGHT_BRACE);
        break;
      case '-':
        addToken(TokenType.MINUS);
        break;
      case '+':
        addToken(TokenType.PLUS);
        break;
      case '*':
        addToken(TokenType.STAR);
        break;
      case '/':
        addToken(TokenType.SLASH);
        break;
      case ' ':
      case '\r':
      case '\t':
        // Ignore whitespace.
        break;
      default:
        if (isDigit(c)) {
          number();
        } else if (isAlpha(c)) {
          identifier();
        } else {
          System.out.println("Invalid Character!" + c);
        }
    }
  }

  /*
   * Advance the scanner to next character in source code.
   *
   * @return char Current character in source code.
   */
  private char advance() {
    return source.charAt(this.current++);
  }

  /*
   * Adds token with the specified type to the list of tokens.
   *
   * @param type The type of token to be added.
   */
  private void addToken(TokenType type) {
    addToken(type, null);
  }

  /*
   * Adds token with the specifie type and literal value to the list of tokens.
   *
   * @param type The type of token to be added.
   *
   * @param literal The literal value associated with the token.
   */
  private void addToken(TokenType type, Object literal) {
    String text = source.substring(this.start, this.current);
    tokens.add(new Token(type, text, literal));
  }

  /*
   * Get character at the current position in the source code.
   *
   * @return char Current character in source code.
   */
  private char peek() {
    if (isAtEnd()) return '\0';
    return source.charAt(current);
  }

  /*
   * Checks if character is digit or not.
   *
   * @param c Character to check.
   *
   * @return boolean if character is digit returns true, false otherwise.
   */
  private boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  /*
   * Checks if character is alphabetic or not.
   *
   * @param c Character to check.
   *
   * @return boolean if character is alphabetic returns true, false otherwise.
   */
  private boolean isAlpha(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
  }

  /*
   * Scans the number in source code from start poistion to create token and add that token to the
   * list.
   */
  private void number() {
    while (isDigit(peek())) advance();

    addToken(TokenType.NUMBER, Integer.parseInt(source.substring(start, current)));
  }

  /*
   * Checks if character is alphabetic or numeric.
   *
   * @param c Character to check.
   *
   * @return boolean, if character is alphanumeric returns true, false otherwise.
   */
  private boolean isAlphaNumeric(char c) {
    return isAlpha(c) || isDigit(c);
  }

  /*
   * Scans identifier in source code from start position to create token and add that token to the
   * list.
   */
  private void identifier() {
    while (isAlphaNumeric(peek())) advance();
    addToken(TokenType.IDENTIFIER);
  }
}
