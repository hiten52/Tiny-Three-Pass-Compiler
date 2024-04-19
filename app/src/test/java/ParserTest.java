import static org.junit.Assert.*;

import ast.BinOp;
import ast.Expr;
import ast.UnOp;
import java.util.Arrays;
import org.junit.Test;
import parser.Parser;
import token.Token;
import token.TokenType;

public class ParserTest {

  private Parser createParserWithTokens(Token... tokens) {
    return new Parser(Arrays.asList(tokens));
  }

  @Test
  public void testSimpleExpression() {
    // Creating a parser with an expression: 1 + 2
    Parser parser =
        createParserWithTokens(
            new Token(TokenType.NUMBER, "1", 1),
            new Token(TokenType.PLUS, "+", null),
            new Token(TokenType.NUMBER, "2", 2),
            new Token(TokenType.EOF, "", null));

    Expr result = parser.generateAST();
    assertTrue("Result must be a BinOp", result instanceof BinOp);
    BinOp op = (BinOp) result;

    assertTrue("Left operand must be UnOp", op.left instanceof UnOp);
    assertTrue("Right operand must be UnOp", op.right instanceof UnOp);
    assertEquals("Operator must be +", "+", op.operator.lexeme);

    UnOp left = (UnOp) op.left;
    UnOp right = (UnOp) op.right;

    assertEquals("Left number must be 1", 1, left.literal);
    assertEquals("Right number must be 2", 2, right.literal);
  }

  @Test
  public void testParentheses() {
    // Testing expression: (1 + 2)
    Parser parser =
        createParserWithTokens(
            new Token(TokenType.LEFT_PAREN, "(", null),
            new Token(TokenType.NUMBER, "1", 1),
            new Token(TokenType.PLUS, "+", null),
            new Token(TokenType.NUMBER, "2", 2),
            new Token(TokenType.RIGHT_PAREN, ")", null),
            new Token(TokenType.EOF, "", null));

    Expr result = parser.generateAST();
    assertTrue("Result must be a BinOp", result instanceof BinOp);
    BinOp op = (BinOp) result;

    assertEquals("Operator must be +", "+", op.operator.lexeme);
  }
}
