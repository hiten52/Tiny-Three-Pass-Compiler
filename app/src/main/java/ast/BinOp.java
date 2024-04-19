package ast;

import token.Token;

/*
 * BinOp class represents Binary operation in AST.
 */
public class BinOp extends Expr {
  public final Expr left; // Left operand of binary operation.
  public final Token operator; // Operator of binary operation.
  public final Expr right; // Right operand of binary operation.

  /*
   * Create new instance of BinOp.
   */
  public BinOp(Token operator, Expr left, Expr right) {
    this.left = left;
    this.operator = operator;
    this.right = right;
  }

  @Override
  public String toString() {
    return String.format("new BinOp(%s, %s, %s)", this.operator, this.left, this.right);
  }
}
