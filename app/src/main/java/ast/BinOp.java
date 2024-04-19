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

  /*
   * Returns asm instructions for this binary operation.
   *
   * @return String A string representation of the asm instuctions;
   */
  @Override
  public String toAsm() {
    StringBuilder asm = new StringBuilder();
    // Generate ASM for the left side and store the result in R0
    asm.append(left.toAsm());
    // Push R0 to stack to preserve it
    asm.append("PU\n");
    // Generate ASM for the right side and store the result in R0
    asm.append(right.toAsm());
    // Swap R0 and R1 to prepare for the operation
    asm.append("SW\n");
    // Pop the previous R0 from stack to R1
    asm.append("PO\n");
    switch (operator.type) {
      case PLUS:
        asm.append("AD\n");
        break;
      case MINUS:
        asm.append("SU\n");
        break;
      case STAR:
        asm.append("MU\n");
        break;
      case SLASH:
        asm.append("DI\n");
        break;
      default:
        break;
    }
    return asm.toString();
  }

  /*
   * Returns a string representation of the BinaryOp object.
   *
   * @return String A string representation of the BinaryOp;
   */
  @Override
  public String toString() {
    return String.format("new BinOp(%s, %s, %s)", this.operator, this.left, this.right);
  }
}
