import org.junit.Test;


import static org.junit.Assert.assertEquals;

import util.MathUtils;

/**
 * Tests for the MathUtils class.
 */
public class UtilitiesTest {
  MathUtils utils = new MathUtils();

  @Test
  public void testInterpolate() {
    assertEquals(5, utils.interpolate(0, 0, 10, 10, 5));
  }
}
