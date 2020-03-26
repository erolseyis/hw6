import org.junit.Test;

import java.awt.Color;


import static org.junit.Assert.assertEquals;

import Utilities.MathUtils;


public class UtilitiesTest {
  MathUtils utils = new MathUtils();

  @Test
  public void testInterpolate() {
    assertEquals(5, utils.interpolate(0, 0, 10, 10, 5));
  }
}
