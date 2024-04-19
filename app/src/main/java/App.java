import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import asm.AsmGenerator;
import parser.Parser;
import token.Token;
import scanner.Scanner;
import simulator.Simulator;
import ast.Expr;
import optimization.ConstantFolder;
import java.util.ArrayList;


public class App {
  public static void main(String[] args) throws IOException {
    runPrompt();
  }

  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    for (;;) {
      System.out.print("> Enter command: ");
      String command = reader.readLine();
      if (command == null) {
        break;
      }

      System.out.print("> Enter arguments (space-separated integers): ");
      String line = reader.readLine();
      if (line == null) {
        continue;
      }

      ArrayList<Integer> simulatorArgs = new ArrayList<>();
      String[] parts = line.trim().split("\\s+");
      try {
        for (String part : parts) {
          simulatorArgs.add(Integer.parseInt(part));
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter only integers.");
        continue;
      }

      run(command, simulatorArgs);
    }
  }

  /*
   * Executes the simulation for a given command and list of arguments.
   * 
   * @param source The source command to be processed.
   * 
   * @param simulatorArgs A list of integers representing arguments for the simulation.
   */
  static void run(String source, ArrayList<Integer> simulatorArgs) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();

    Parser parser = new Parser(tokens);
    Expr ast = parser.generateAST();

    ConstantFolder constantFolder = new ConstantFolder();
    Expr optimizedAst = constantFolder.foldConstants(ast);

    AsmGenerator asmGenerator = new AsmGenerator(optimizedAst);
    ArrayList<String> asmInstructions = asmGenerator.getAsmInstructions();

    Simulator simulator = new Simulator();
    simulator.simulate(asmInstructions, simulatorArgs);

    System.out.println(simulator.getR0()); // final result
  }
}
