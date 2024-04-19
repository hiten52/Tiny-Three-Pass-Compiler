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

  /*
   * Returns asm instructions for this unary operation.
   *
   * @return String A string representation of the asm instuctions;
   */
  @Override
  public String toAsm() {
    if (type.equals(TokenType.arg)) {
      return "AR " + literal + "\n";
    } else if (type.equals(TokenType.imm)) {
      return "IM " + literal + "\n";
    }
    return "";
  }

  /*
   * Returns a string representation of the UnOp object.
   *
   * @return String A string representation of the UnOp;
   */
  @Override
  public String toString() {
    return String.format("new UnOp(%s, %s)", this.type, this.literal);
  }
}
