package simulator;

import java.util.ArrayList;
import java.util.Stack;

/*
 * Simple stack machine with two registers.
 */
public class Simulator {
  ArrayList<String> asm; // Assembly instructions
  ArrayList<Integer> args; // Arguments for instructions
  Integer r0; // Register 0
  Integer r1; // Register 1
  Integer ip; // Instruction Pointer
  Stack<Integer> stack;

  /*
   * Create a machine with pre-defined assembly code and arguments.
   * @param asm ArrayList with assembly code
   * @param args ArrayList of arguments as integer
   */
  public Simulator(ArrayList<String> asm, ArrayList<Integer> args) {
    this.asm = asm;
    this.args = args;
    this.r0 = null;
    this.r1 = null;
    this.ip = 0;
    stack = new Stack<Integer>();
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
