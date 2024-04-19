import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import simulator.Simulator;

public class SimulatorTest {
  private Simulator simulator;

  @Before
  public void setUp() {
    simulator = new Simulator();
  }

  @Test
  public void testSimpleArithmetic() {
    ArrayList<String> asm = new ArrayList<>();
    asm.add("IM 5");
    asm.add("PU");
    asm.add("IM 3");
    asm.add("SW");
    asm.add("PO");
    asm.add("SU");
    ArrayList<Integer> args = new ArrayList<>();

    simulator.simulate(asm, args);

    assertEquals(
        "Result of R0 should be 2 after subtraction",
        Integer.valueOf(2),
        (Integer) simulator.getR0());
  }

  @Test
  public void testDivision() {
    ArrayList<String> asm = new ArrayList<>();
    asm.add("IM 10");
    asm.add("PU");
    asm.add("IM 2");
    asm.add("SW");
    asm.add("PO");
    asm.add("DI");
    ArrayList<Integer> args = new ArrayList<>();

    simulator.simulate(asm, args);

    assertEquals(
        "Result of R0 should be 5 after division", Integer.valueOf(5), (Integer) simulator.getR0());
  }
}
