import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {
  @Test
  public void appHasAGreeting() {
    App classUnderTest = new App();
    assertNotNull("app should have a greeting", classUnderTest.getGreeting());
  }
}
