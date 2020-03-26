import model.KeyFrame;
import model.Position;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the KeyFrame class.
 */
public class KeyFrameTest {

  Position p1 = new Position(100, 100);
  Position p2 = null;
  Position p3 = new Position(-5, -120);

  @Test(expected = IllegalArgumentException.class)
  public void illegalColorTest() {
    KeyFrame illegalKF1 = new KeyFrame(null, 10, 10, p1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalWidthTest() {
    KeyFrame illegalKF2 = new KeyFrame(null, -10, 10, p1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalHeigthTest() {
    KeyFrame illegalKF3 = new KeyFrame(Color.RED, 10, -10, p1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalWidthHeightTest() {
    KeyFrame illegalKF4 = new KeyFrame(Color.RED, -10, -110, p1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalPositionTest() {
    KeyFrame illegalKF5 = new KeyFrame(Color.RED, -10, -110, p2);
  }

  @Test
  public void toStringTest() {
    KeyFrame kf1 = new KeyFrame(Color.RED, 100, 100, p1);
    KeyFrame kf2 = new KeyFrame(Color.GREEN, 250, 200, p3);
    assertEquals(
            "X: 100\tY: 100\tTheta: 0.0\tHeight: 100\tWidth: 100\tColor: " +
                    "java.awt.Color[r=255,g=0,b=0]",
            kf1.toString());
    assertEquals(
            "X: -5\tY: -120\tTheta: 0.0\tHeight: 200\tWidth: 250\tColor: " +
                    "java.awt.Color[r=0,g=255,b=0]",
            kf2.toString());
  }
}