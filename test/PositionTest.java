import model.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Position class.
 */
public class PositionTest {

  Position pos1 = new Position(69, 420);
  Position pos2 = new Position(-1, 0);

  // toString()

  @Test
  public void testToString() {
    assertEquals("\tX: 69\tY: 420", pos1.toString());
    assertEquals("\tX: -1\tY: 0", pos2.toString());
  }
}
