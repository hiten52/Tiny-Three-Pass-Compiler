import static org.junit.Assert.assertEquals;

import org.junit.Test;
import token.Token;
import token.TokenType;

public class TokenTest {
  @Test
  public void tokentoString() {
    Token token = new Token(TokenType.LEFT_PAREN, "(", null);
    assertEquals(token.toString(), "LEFT_PAREN ( null");

    token = new Token(TokenType.NUMBER, "12", 12);
    assertEquals(token.toString(), "NUMBER 12 12");
  }
}
