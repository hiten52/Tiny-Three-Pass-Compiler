import static org.junit.Assert.assertEquals;

import org.junit.Test;
import scanner.Scanner;
import java.util.List;
import token.Token;
import token.TokenType;

public class ScannerTest {
    @Test
    public void testBasicTokens() {
        Scanner scanner = new Scanner("[]()+-*/");
        List<Token> tokens = scanner.scanTokens();
        assertEquals("Unexpected number of tokens", 9, tokens.size());
        assertEquals(TokenType.LEFT_BRACE, tokens.get(0).type);
        assertEquals(TokenType.RIGHT_BRACE, tokens.get(1).type);
        assertEquals(TokenType.LEFT_PAREN, tokens.get(2).type);
        assertEquals(TokenType.RIGHT_PAREN, tokens.get(3).type);
        assertEquals(TokenType.PLUS, tokens.get(4).type);
        assertEquals(TokenType.MINUS, tokens.get(5).type);
        assertEquals(TokenType.STAR, tokens.get(6).type);
        assertEquals(TokenType.SLASH, tokens.get(7).type);
        assertEquals(TokenType.EOF, tokens.get(8).type);
    }

    @Test
    public void testComplexExpression() {
        Scanner scanner = new Scanner("[xe y] (x * 1 + y)");
        List<Token> tokens = scanner.scanTokens();
        assertEquals("Unexpected number of tokens", 12, tokens.size());
        assertEquals("LEFT_BRACE [", tokens.get(0).toString());
        assertEquals("IDENTIFIER xe", tokens.get(1).toString());
        assertEquals("IDENTIFIER y", tokens.get(2).toString());
        assertEquals("RIGHT_BRACE ]", tokens.get(3).toString());
        assertEquals("LEFT_PAREN (", tokens.get(4).toString());
        assertEquals("IDENTIFIER x", tokens.get(5).toString());
        assertEquals("STAR *", tokens.get(6).toString());
        assertEquals("NUMBER 1 1", tokens.get(7).toString());
        assertEquals("PLUS +", tokens.get(8).toString());
        assertEquals("IDENTIFIER y", tokens.get(9).toString());
        assertEquals("RIGHT_PAREN )", tokens.get(10).toString());
        assertEquals("EOF ", tokens.get(11).toString());
    }
}
