package simulator;

import java.util.ArrayList;
import java.util.Stack;

/*
 * Simple stack machine with two registers.
 */
public class Simulator {
  Integer r0; // Register 0
  Integer r1; // Register 1
  Integer ip; // Instruction Pointer
  Stack<Integer> stack;

  /*
   * Create a machine with pre-defined assembly code and arguments.
   */
  public Simulator() {
    this.r0 = null;
    this.r1 = null;
    this.ip = 0;
    stack = new Stack<Integer>();
  }

  /*
   * Simulates the machine based on the provided assembly instructions.
   *
   * @param asm ArrayList with assembly code
   * @param args ArrayList of arguments as integer
   */
  @SuppressWarnings("null")
  public void simulate(ArrayList<String> asm, ArrayList<Integer> args) {
    while (ip < asm.size()) {
      String instruct = asm.get(ip);
      String[] parts = instruct.split("\\s+");
      String ins = parts[0];
      Integer n = parts.length > 1 ? Integer.parseInt(parts[1]) : null;

      switch (ins) {
        case "IM":
          r0 = n;
          break;
        case "AR":
          r0 = args.get(n);
          break;
        case "SW":
          int tmp = r0;
          r0 = r1;
          r1 = tmp;
          break;
        case "PU":
          stack.push(r0);
          break;
        case "PO":
          r0 = stack.pop();
          break;
        case "AD":
          r0 += r1;
          break;
        case "SU":
          r0 -= r1;
          break;
        case "MU":
          r0 *= r1;
          break;
        case "DI":
          r0 /= r1;
          break;
      }
      ip++;
    }
  }

  /*
   * Returns the value of register 0. All calculations final result will be stored in r0.
   *
   * @return int Returns the value of register 0.
   */
  public int getR0() {
    return this.r0;
  }

  /**
   * Returns a string representation of current machine state.
   *
   * @return A string represenatation of current machine state, including registers values,
   *     intruction pointer andd stack.
   */
  public String toString() {
    String r0RegStr = String.format("%d\n", this.r0);
    String r1RegStr = String.format("%d\n", this.r1);
    String ipStr = String.format("%d\n", this.ip);
    String stackStr = stack.toString();
    return String.format("%s%s%s%s", r0RegStr, r1RegStr, ipStr, stackStr);
  }
}
