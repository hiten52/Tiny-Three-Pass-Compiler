package token;

/*
 * Enum of Possible Token types.
 */
public enum TokenType {
  // Single-character tokens.
  LEFT_PAREN,
  RIGHT_PAREN,
  LEFT_BRACE,
  RIGHT_BRACE,
  MINUS,
  PLUS,
  SLASH,
  STAR,

  // Literals.
  IDENTIFIER,
  STRING,
  NUMBER,

  arg,
  imm,

  EOF
}
