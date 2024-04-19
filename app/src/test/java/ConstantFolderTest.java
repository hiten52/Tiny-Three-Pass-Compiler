import static org.junit.Assert.*;

import ast.BinOp;
import ast.Expr;
import ast.UnOp;
import optimization.ConstantFolder;
import org.junit.Before;
import org.junit.Test;
import token.Token;
import token.TokenType;

public class ConstantFolderTest {
  private ConstantFolder constantFolder;

  @Before
  public void setUp() {
    constantFolder = new ConstantFolder();
  }

  @Test
  public void testAdditionOfConstants() {
    UnOp left = new UnOp(TokenType.imm, 3);
    UnOp right = new UnOp(TokenType.imm, 4);
    Token plus = new Token(TokenType.PLUS, "+", null);
    BinOp binOp = new BinOp(plus, left, right);

    Expr result = constantFolder.foldConstants(binOp);
    assertTrue(result instanceof UnOp);
    assertEquals(7, ((UnOp) result).literal);
  }

  @Test
  public void testSubtractionOfConstants() {
    UnOp left = new UnOp(TokenType.imm, 10);
    UnOp right = new UnOp(TokenType.imm, 4);
    Token minus = new Token(TokenType.MINUS, "-", null);
    BinOp binOp = new BinOp(minus, left, right);

    Expr result = constantFolder.foldConstants(binOp);
    assertTrue(result instanceof UnOp);
    assertEquals(6, ((UnOp) result).literal);
  }

  @Test
  public void testMultiplicationOfConstants() {
    UnOp left = new UnOp(TokenType.imm, 5);
    UnOp right = new UnOp(TokenType.imm, 6);
    Token star = new Token(TokenType.STAR, "*", null);
    BinOp binOp = new BinOp(star, left, right);

    Expr result = constantFolder.foldConstants(binOp);
    assertTrue(result instanceof UnOp);
    assertEquals(30, ((UnOp) result).literal);
  }

  @Test
  public void testDivisionOfConstants() {
    UnOp left = new UnOp(TokenType.imm, 20);
    UnOp right = new UnOp(TokenType.imm, 5);
    Token slash = new Token(TokenType.SLASH, "/", null);
    BinOp binOp = new BinOp(slash, left, right);

    Expr result = constantFolder.foldConstants(binOp);
    assertTrue(result instanceof UnOp);
    assertEquals(4, ((UnOp) result).literal);
  }
}
