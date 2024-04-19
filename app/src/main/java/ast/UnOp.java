package ast;

import token.TokenType;

/*
 * UnOp class represents unary operations in AST.
 */
public class UnOp extends Expr {
  public final TokenType type; // Type of unary operation.
  public final int literal; // Operand of unary operaion.

  /*
   * Create new instance of UnOp.
   */
  public UnOp(TokenType type, int literal) {
    this.type = type;
    this.literal = literal;
  }

  @Override
  public String toString() {
    return String.format("new UnOp(%s, %s)", this.type, this.literal);
  }
}
