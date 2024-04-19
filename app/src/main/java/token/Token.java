package token;

/*
 * Token class for storing information about lexical units.
 */
public class Token {
  public final TokenType type; // Type of the token
  public String lexeme; // Characters forming the token
  public Object literal; // Literal value associated with token

  /*
   * Create new instance of Token
   *
   * @param type Type of the token
   * 
   * @param lexeme Characters forming the token
   * 
   * @param literal Literal value associated with token
   */
  public Token(TokenType type, String lexeme, Object literal) {
    this.type = type;
    this.lexeme = lexeme;
    this.literal = literal;
  }

  /*
   * Returns a string representation of the Token object.
   *
   * @return A string representation of the token, including its type, lexeme, and literal value.
   */
  @Override
  public String toString() {
    return type + " " + lexeme + (literal != null ? " " + literal : "");
  }
}
