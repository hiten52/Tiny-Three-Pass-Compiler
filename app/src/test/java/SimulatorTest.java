import static org.junit.Assert.assertEquals;

import org.junit.Test;
import simulator.Simulator;

public class SimulatorTest {
  @Test
  public void simulatortoString() {
    Simulator simulator = new Simulator(null, null);
    assertEquals(simulator.toString(), "null\nnull\n0\n[]");
  }
}
