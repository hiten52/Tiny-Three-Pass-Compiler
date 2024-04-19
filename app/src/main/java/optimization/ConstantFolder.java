package optimization;

import ast.BinOp;
import ast.Expr;
import ast.UnOp;
import token.TokenType;

/*
 * ConstantFolder class apply constant folding on AST to optimize it.
 */
public class ConstantFolder {
  /*
   * Takes AST and applies constant folding to optimize it.
   *
   * @param ast The root of the AST to be optimized.
   * @return Expr The optimized AST after applying constant folding.
   */
  public Expr foldConstants(Expr ast) {
    if (ast instanceof BinOp) {
      BinOp binOp = (BinOp) ast;
      Expr left = foldConstants(binOp.left);
      Expr right = foldConstants(binOp.right);

      if (left instanceof UnOp && right instanceof UnOp) {
        UnOp leftUnOp = (UnOp) left;
        UnOp rightUnOp = (UnOp) right;

        // Check if both operands are immediate values (constants)
        if (leftUnOp.type == TokenType.imm && rightUnOp.type == TokenType.imm) {
          int leftVal = (Integer) leftUnOp.literal;
          int rightVal = (Integer) rightUnOp.literal;

          switch (binOp.operator.type) {
            case PLUS:
              return new UnOp(TokenType.imm, leftVal + rightVal);
            case MINUS:
              return new UnOp(TokenType.imm, leftVal - rightVal);
            case STAR:
              return new UnOp(TokenType.imm, leftVal * rightVal);
            case SLASH:
              if (rightVal != 0) { // Avoid division by zero
                return new UnOp(TokenType.imm, leftVal / rightVal);
              }
              break;
            default:
              break;
          }
        }
      }

      return new BinOp(binOp.operator, left, right);
    } else if (ast instanceof UnOp) {
      return ast;
    }

    return ast;
  }
}
