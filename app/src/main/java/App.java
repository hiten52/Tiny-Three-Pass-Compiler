import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import parser.Parser;
import token.Token;
import scanner.Scanner;
import ast.Expr;
import optimization.ConstantFolder;


public class App {
  public static void main(String[] args) throws IOException {
    runPrompt();
  }

  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    for (;;) {
      System.out.print("> ");
      String line = reader.readLine();
      if (line == null)
        break;
      run(line);
    }
  }

  private static void run(String source) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();
    Parser parser = new Parser(tokens);
    Expr ast = parser.generateAST();
    ConstantFolder constantFolder = new ConstantFolder();
    Expr optimizedAst = constantFolder.foldConstants(ast);
  }
}
