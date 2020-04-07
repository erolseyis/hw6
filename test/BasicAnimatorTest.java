import org.junit.Test;

import java.awt.Color;
import java.util.Map;
import java.util.NavigableMap;

import model.BasicAnimator;
import model.KeyFrame;
import model.Position2D;
import model.ShapeType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the BasicAnimator class.
 */
public class BasicAnimatorTest {

  BasicAnimator a = new BasicAnimator();

  /**
   * Initializes values used throughout the test suite.
   */
  private void init() {
    a = new BasicAnimator();
  }

  // addShape() and getShape()

  @Test
  public void testAddGetShapeRectangle() {
    init();
    a.addShape(ShapeType.RECTANGLE, "R");
    assertEquals(ShapeType.RECTANGLE, a.getShapeType("R"));
  }

  @Test
  public void testAddGetShapeEllipse() {
    init();
    a.addShape(ShapeType.ELLIPSE, "C");
    assertEquals(ShapeType.ELLIPSE, a.getShapeType("C"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullShape() {
    init();
    a.addShape(null, "C");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullName() {
    init();
    a.addShape(ShapeType.ELLIPSE, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeEmptyName() {
    init();
    a.addShape(ShapeType.ELLIPSE, "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNameAlreadyUsed() {
    init();
    a.addShape(ShapeType.ELLIPSE, "C");
    a.addShape(ShapeType.ELLIPSE, "C");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeNullShape() {
    init();
    a.addShape(ShapeType.RECTANGLE, "R");
    a.getShapeType(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeNonexistentShape() {
    init();
    a.addShape(ShapeType.RECTANGLE, "R");
    a.getShapeType("C");
  }

  // addKeyFrame() and getShapesAtTick

  @Test
  public void testAddKeyFrameAndGetShapesAtTick() {
    init();
    a.addShape(ShapeType.RECTANGLE, "R");
    a.addKeyFrame("R", 1, new KeyFrame(Color.RED,
        50, 100, new Position2D(200, 200)));

    a.addKeyFrame("R", 10, new KeyFrame(Color.RED,
        50, 100, new Position2D(10, 200)));

    a.addKeyFrame("R", 50, new KeyFrame(Color.RED,
        50, 100, new Position2D(300, 300)));

    a.addShape(ShapeType.ELLIPSE, "C");

    a.addKeyFrame("C", 5, new KeyFrame(Color.RED,
        400, 49, new Position2D(0, 0)));

    a.addKeyFrame("C", 15, new KeyFrame(Color.BLUE,
        440, 89, new Position2D(840, 138)));

    Map<String, KeyFrame> shapesTick1 = a.getShapesAtTick(1);
    Map<String, KeyFrame> shapesTick10 = a.getShapesAtTick(10);
    Map<String, KeyFrame> shapesTick50 = a.getShapesAtTick(50);
    Map<String, KeyFrame> shapesTick51 = a.getShapesAtTick(51);

    // Ensure that the shapes only show up if they're supposed to appear at that tick
    assertEquals(1, shapesTick1.size());
    assertTrue(shapesTick1.containsKey("R"));

    assertEquals(2, shapesTick10.size());
    assertTrue(shapesTick10.containsKey("R") && shapesTick10.containsKey("C"));

    assertEquals(2, shapesTick50.size());
    assertTrue(shapesTick50.containsKey("R") && shapesTick10.containsKey("C"));

    assertEquals(2, shapesTick51.size());
    assertTrue(shapesTick50.containsKey("R") && shapesTick10.containsKey("C"));

    // Ensure that we get the right values if the tick is the same as one of the added KF's
    assertEquals(Color.RED, shapesTick1.get("R").getColor());
    assertEquals(50, shapesTick1.get("R").getWidth());
    assertEquals(100, shapesTick1.get("R").getHeight());
    assertEquals(200, shapesTick1.get("R").getPosition().getX());
    assertEquals(200, shapesTick1.get("R").getPosition().getY());

    assertEquals(Color.RED, shapesTick1.get("R").getColor());
    assertEquals(50, shapesTick10.get("R").getWidth());
    assertEquals(100, shapesTick10.get("R").getHeight());
    assertEquals(10, shapesTick10.get("R").getPosition().getX());
    assertEquals(200, shapesTick10.get("R").getPosition().getY());

    // Ensure that we've interpolated all values correctly if the tick is between two added KF's
    assertEquals(new Color(127, 0, 127), shapesTick10.get("C").getColor());
    assertEquals(420, shapesTick10.get("C").getWidth());
    assertEquals(69, shapesTick10.get("C").getHeight());
    assertEquals(420, shapesTick10.get("C").getPosition().getX());
    assertEquals(69, shapesTick10.get("C").getPosition().getY());

    assertEquals(Color.RED, shapesTick50.get("R").getColor());
    assertEquals(50, shapesTick50.get("R").getWidth());
    assertEquals(100, shapesTick50.get("R").getHeight());
    assertEquals(300, shapesTick50.get("R").getPosition().getX());
    assertEquals(300, shapesTick50.get("R").getPosition().getY());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullNamedKeyFrame() {
    init();
    a.addShape(ShapeType.RECTANGLE, "R");
    a.addKeyFrame(null, 1, new KeyFrame(Color.RED,
        50, 100, new Position2D(200, 200)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyFrameForNonexistentShape() {
    init();
    a.addShape(ShapeType.RECTANGLE, "R");
    a.addKeyFrame("R2", 1, new KeyFrame(Color.RED,
        50, 100, new Position2D(200, 200)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyFrameBadTick() {
    init();
    a.addShape(ShapeType.RECTANGLE, "R");
    a.addKeyFrame("R", 0, new KeyFrame(Color.RED,
        50, 100, new Position2D(200, 200)));
  }

  // addMotion()

  @Test
  public void testAddMotion() {
    init();
    a.addShape(ShapeType.RECTANGLE, "R");
    a.addMotion("R", 11, new KeyFrame(Color.RED,
            50, 100, new Position2D(200, 200)),
        20, new KeyFrame(Color.RED,
            50, 100, new Position2D(10, 200)));

    a.addMotion("R", 20, new KeyFrame(Color.RED,
            50, 100, new Position2D(10, 200)),
        50, new KeyFrame(Color.RED,
            50, 100, new Position2D(300, 300)));

    a.addMotion("R", 1, new KeyFrame(Color.RED,
            25, 50, new Position2D(100, 100)),
        11, new KeyFrame(Color.RED,
            50, 100, new Position2D(200, 200)));

    NavigableMap<Integer, KeyFrame> kfs = a.getShapeKeyFrames("R");
    assertEquals(4, kfs.size());
    assertTrue(kfs.containsKey(1) && kfs.containsKey(11) && kfs.containsKey(20)
        && kfs.containsKey(50));

    assertEquals(Color.RED, kfs.get(1).getColor());
    assertEquals(25, kfs.get(1).getWidth());
    assertEquals(50, kfs.get(1).getHeight());
    assertEquals(100, kfs.get(1).getPosition().getX());
    assertEquals(100, kfs.get(1).getPosition().getY());

    assertEquals(Color.RED, kfs.get(11).getColor());
    assertEquals(50, kfs.get(11).getWidth());
    assertEquals(100, kfs.get(11).getHeight());
    assertEquals(200, kfs.get(11).getPosition().getX());
    assertEquals(200, kfs.get(11).getPosition().getY());

    assertEquals(Color.RED, kfs.get(20).getColor());
    assertEquals(50, kfs.get(20).getWidth());
    assertEquals(100, kfs.get(20).getHeight());
    assertEquals(10, kfs.get(20).getPosition().getX());
    assertEquals(200, kfs.get(20).getPosition().getY());

    assertEquals(Color.RED, kfs.get(50).getColor());
    assertEquals(50, kfs.get(50).getWidth());
    assertEquals(100, kfs.get(50).getHeight());
    assertEquals(300, kfs.get(50).getPosition().getX());
    assertEquals(300, kfs.get(50).getPosition().getY());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionConflictingKeyFrames() {
    init();
    a.addShape(ShapeType.RECTANGLE, "R");
    a.addMotion("R", 11, new KeyFrame(Color.RED,
            50, 100, new Position2D(200, 200)),
        20, new KeyFrame(Color.RED,
            50, 100, new Position2D(10, 200)));
    a.addMotion("R", 20, new KeyFrame(Color.RED,
            50, 10, new Position2D(10, 200)),
        50, new KeyFrame(Color.RED,
            50, 100, new Position2D(300, 300)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionAppendTeleportation() {
    init();
    a.addShape(ShapeType.RECTANGLE, "R");
    a.addMotion("R", 11, new KeyFrame(Color.RED,
            50, 100, new Position2D(200, 200)),
        20, new KeyFrame(Color.RED,
            50, 100, new Position2D(10, 200)));
    a.addMotion("R", 21, new KeyFrame(Color.RED,
            50, 10, new Position2D(10, 200)),
        50, new KeyFrame(Color.RED,
            50, 100, new Position2D(300, 300)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionPrependConflictingTimes() {
    init();
    a.addShape(ShapeType.RECTANGLE, "R");
    a.addMotion("R", 11, new KeyFrame(Color.RED,
            50, 100, new Position2D(200, 200)),
        20, new KeyFrame(Color.RED,
            50, 100, new Position2D(10, 200)));
    a.addMotion("R", 1, new KeyFrame(Color.RED,
            50, 100, new Position2D(10, 200)),
        12, new KeyFrame(Color.RED,
            50, 100, new Position2D(300, 300)));
  }

  // toString()

  @Test
  public void testEmptyAnimator() {
    init();
    assertEquals("", a.toString());
  }

  @Test
  public void testAssignmentExample() {
    // This test mimics the sample directions:

    //Create rectangle named "R"
    //Create oval named "C"
    //
    //From time 1 to 10, R moves from (200,200) to (10,200), stays size 50x100, and stays red.
    //From time 10 to 50, R moves from (10,200) to (300,300), stays size 50x100, and stays red.
    //From time 50 to 51, R does nothing.
    //From time 51 to 70, R stays put, shrinks from 50x100 to 25x100, and stays red.
    //From time 70 to 100, R moves from (300,300) to (200,200), stays size 25x.100, and stays red.
    //
    //From time 6 to 20, C stays put at (440,70), stays size 120x60, and stays blue.
    //From time 20 to 50, C moves from (440,70) to (440,250), stays size 120x60 and stays blue.
    //From time 50 to 70, C moves from (440,250) to (440,370), stays size 120x60, and turns
    //    green-blue.
    //From time 70 to 80, C stays put at (440,370), stays size 120x60, and turns green.
    //From time 80 to 100, C stays put at (440,370), stays size 120x60, and stays green.

    init();

    a.addShape(ShapeType.RECTANGLE, "R");

    a.addShape(ShapeType.ELLIPSE, "C");
    // R's KeyFrame's
    a.addKeyFrame("R", 1, new KeyFrame(Color.RED,
        50, 100, new Position2D(200, 200)));

    a.addKeyFrame("R", 10, new KeyFrame(Color.RED,
        50, 100, new Position2D(10, 200)));

    a.addKeyFrame("R", 50, new KeyFrame(Color.RED,
        50, 100, new Position2D(300, 300)));

    a.addKeyFrame("R", 51, new KeyFrame(Color.RED,
        50, 100, new Position2D(300, 300)));

    a.addKeyFrame("R", 70, new KeyFrame(Color.RED,
        25, 100, new Position2D(300, 300)));

    a.addKeyFrame("R", 100, new KeyFrame(Color.RED,
        25, 100, new Position2D(200, 200)));

    // C's KeyFrame's
    a.addKeyFrame("C", 6, new KeyFrame(Color.BLUE,
        120, 60, new Position2D(440, 70)));

    a.addKeyFrame("C", 20, new KeyFrame(Color.BLUE,
        120, 60, new Position2D(440, 70)));

    a.addKeyFrame("C", 50, new KeyFrame(Color.BLUE,
        120, 60, new Position2D(440, 250)));

    a.addKeyFrame("C", 70, new KeyFrame(Color.CYAN,
        120, 60, new Position2D(440, 370))); // "green-blue"?

    a.addKeyFrame("C", 80, new KeyFrame(Color.GREEN,
        120, 60, new Position2D(440, 370)));

    a.addKeyFrame("C", 100, new KeyFrame(Color.GREEN,
        120, 60, new Position2D(440, 370)));

    String exp = "shape R rectangle\n"
        +
        "motion R\n"
        +
        "start:\tTick: 1\tX: 200\tY: 200\tHeight: 100\tWidth: 50\tColor: "
        +
        "java.awt.Color[r=255,g=0,b=0]\n"
        +
        "end:\tTick: 10\tX: 10\tY: 200\tHeight: 100\tWidth: 50\tColor: "
        +
        "java.awt.Color[r=255,g=0,b=0]\n"
        +
        "motion R\n"
        +
        "start:\tTick: 10\tX: 10\tY: 200\tHeight: 100\tWidth: 50\tColor: "
        +
        "java.awt.Color[r=255,g=0,b=0]\n"
        +
        "end:\tTick: 50\tX: 300\tY: 300\tHeight: 100\tWidth: 50\tColor: "
        +
        "java.awt.Color[r=255,g=0,b=0]\n"
        +
        "motion R\n"
        +
        "start:\tTick: 50\tX: 300\tY: 300\tHeight: 100\tWidth: 50\tColor: "
        +
        "java.awt.Color[r=255,g=0,b=0]\n"
        +
        "end:\tTick: 51\tX: 300\tY: 300\tHeight: 100\tWidth: 50\tColor: "
        +
        "java.awt.Color[r=255,g=0,b=0]\n"
        +
        "motion R\n"
        +
        "start:\tTick: 51\tX: 300\tY: 300\tHeight: 100\tWidth: 50\tColor: "
        +
        "java.awt.Color[r=255,g=0,b=0]\n"
        +
        "end:\tTick: 70\tX: 300\tY: 300\tHeight: 100\tWidth: 25\tColor: "
        +
        "java.awt.Color[r=255,g=0,b=0]\n"
        +
        "motion R\n"
        +
        "start:\tTick: 70\tX: 300\tY: 300\tHeight: 100\tWidth: 25\tColor: "
        +
        "java.awt.Color[r=255,g=0,b=0]\n"
        +
        "end:\tTick: 100\tX: 200\tY: 200\tHeight: 100\tWidth: 25\tColor: "
        +
        "java.awt.Color[r=255,g=0,b=0]\n"
        +
        "shape C ellipse\n"
        +
        "motion C\n"
        +
        "start:\tTick: 6\tX: 440\tY: 70\tHeight: 60\tWidth: 120\tColor: "
        +
        "java.awt.Color[r=0,g=0,b=255]\n"
        +
        "end:\tTick: 20\tX: 440\tY: 70\tHeight: 60\tWidth: 120\tColor: "
        +
        "java.awt.Color[r=0,g=0,b=255]\n"
        +
        "motion C\n"
        +
        "start:\tTick: 20\tX: 440\tY: 70\tHeight: 60\tWidth: 120\tColor: "
        +
        "java.awt.Color[r=0,g=0,b=255]\n"
        +
        "end:\tTick: 50\tX: 440\tY: 250\tHeight: 60\tWidth: 120\tColor: "
        +
        "java.awt.Color[r=0,g=0,b=255]\n"
        +
        "motion C\n"
        +
        "start:\tTick: 50\tX: 440\tY: 250\tHeight: 60\tWidth: 120\tColor: "
        +
        "java.awt.Color[r=0,g=0,b=255]\n"
        +
        "end:\tTick: 70\tX: 440\tY: 370\tHeight: 60\tWidth: 120\tColor: "
        +
        "java.awt.Color[r=0,g=255,b=255]\n"
        +
        "motion C\n"
        +
        "start:\tTick: 70\tX: 440\tY: 370\tHeight: 60\tWidth: 120\tColor: "
        +
        "java.awt.Color[r=0,g=255,b=255]\n"
        +
        "end:\tTick: 80\tX: 440\tY: 370\tHeight: 60\tWidth: 120\tColor: "
        +
        "java.awt.Color[r=0,g=255,b=0]\n"
        +
        "motion C\n"
        +
        "start:\tTick: 80\tX: 440\tY: 370\tHeight: 60\tWidth: 120\tColor: "
        +
        "java.awt.Color[r=0,g=255,b=0]\n"
        +
        "end:\tTick: 100\tX: 440\tY: 370\tHeight: 60\tWidth: 120\tColor: "
        +
        "java.awt.Color[r=0,g=255,b=0]\n";

    assertEquals(exp, a.toString());
  }
}
