import model.Position2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Position class.
 */
public class Position2DTest {

  Position2D pos1 = new Position2D(69, 420);
  Position2D pos2 = new Position2D(-1, 0);

  // toString()

  @Test
  public void testToString() {
    assertEquals("\tX: 69\tY: 420", pos1.toString());
    assertEquals("\tX: -1\tY: 0", pos2.toString());
  }
}
