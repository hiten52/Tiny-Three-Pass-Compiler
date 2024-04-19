package parser;

import ast.BinOp;
import ast.Expr;
import ast.UnOp;
import java.util.ArrayList;
import java.util.List;
import token.Token;
import token.TokenType;

/*
 * Parser class parse a list of tokens into an AST.
 */
public class Parser {
  private final List<Token> tokens; // List of tokens
  private int current = 0; // Token to parse currently
  private final List<String> vars; // List of identifiers

  /*
   * Create new instance of Parser with provided list of tokens.
   *
   * @param tokens Token list.
   */
  public Parser(List<Token> tokens) {
    this.tokens = tokens;
    this.vars = new ArrayList<String>();
  }

  /*
   * Generates AST by parsing the tokens passed to the Parser.
   * 
   * @return Expr The root of the AST representing the parsed input.
   */
  public Expr generateAST() {
    return function();
  }

  /*
   * function ::= "[" argList "]" expression
   */
  private Expr function() {
    match(TokenType.LEFT_BRACE);
    argumentList();
    match(TokenType.RIGHT_BRACE);
    Expr expr = expression();

    return expr;
  }

  /*
   * arg-list ::= nothing | variable arg-list
   */
  private void argumentList() {
    while (match(TokenType.IDENTIFIER)) {
      Token var = previous();
      vars.add(var.lexeme);
    }
  }

  /*
   * expression ::= term | expression '+' term | expression '-' term
   */
  private Expr expression() {
    Expr expr = term();

    while (match(TokenType.PLUS, TokenType.MINUS)) {
      Token operator = previous();
      Expr right = term();
      expr = new BinOp(operator, expr, right);
    }

    return expr;
  }

  /*
   * term ::= factor | term '*' factor | term '/' factor
   */
  private Expr term() {
    Expr expr = factor();

    while (match(TokenType.SLASH, TokenType.STAR)) {
      Token operator = previous();
      Expr right = factor();
      expr = new BinOp(operator, expr, right);
    }

    return expr;
  }

  /*
   * factor ::= number | variable | '(' expression ')'
   */
  private Expr factor() {
    if (match(TokenType.MINUS)) {
      Token number = advance();
      number.lexeme = "-" + number.lexeme;
      number.literal = ((Integer) number.literal * -1);
      return new UnOp(TokenType.imm, (Integer) number.literal);
    }

    if (match(TokenType.NUMBER)) {
      return new UnOp(TokenType.imm, (Integer) previous().literal);
    }

    if (match(TokenType.IDENTIFIER)) {
      return new UnOp(TokenType.arg, this.vars.indexOf(previous().lexeme));
    }

    match(TokenType.LEFT_PAREN);
    Expr expr = expression();
    match(TokenType.RIGHT_PAREN);

    return expr;
  }

  /*
   * Consumes current token if token matches specified token types.
   *
   * @param types Token types to match.
   * 
   * @return boolean if match is found returns true, false otherwise.
   */
  private boolean match(TokenType... types) {
    for (TokenType type : types) {
      if (check(type)) {
        advance();
        return true;
      }
    }

    return false;
  }

  /*
   * Checks if the current token is of the specified type.
   *
   * @param type Token type to match.
   * 
   * @return boolean, if match is found returns true, false otherwise.
   */
  private boolean check(TokenType type) {
    if (isAtEnd())
      return false;
    return peek().type == type;
  }

  /*
   * Advance the Parser to next Token.
   *
   * @return Token Current Token.
   */
  private Token advance() {
    if (!isAtEnd())
      current++;
    return previous();
  }

  /*
   * Checks if Parser has reached end of the Token list.
   *
   * @return boolean if parser has reached end of the token list returns true, false otherwise.
   */
  private boolean isAtEnd() {
    return peek().type == TokenType.EOF;
  }

  /*
   * Get token at the current position.
   *
   * @return Token Current Token being parsed.
   */
  private Token peek() {
    return tokens.get(current);
  }

  /*
   * Get previous Token to the current position.
   *
   * @return Token Previous Token to being parsed.
   */
  private Token previous() {
    return tokens.get(current - 1);
  }
}
