package view;

import java.awt.*;
import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.NavigableMap;

import model.Animator;
import model.KeyFrame;
import model.ShapeType;

/**
 * Produces an SVG representation of an animation. StringSustitutor would've been the move for this
 * but I discovered it too far in to bother.
 */
public class SvgView implements IView {

  private Appendable w;

  @Override
  public void render(Animator model, int ticksPerSecond) {
    try {
      StringBuilder s = new StringBuilder();
      // Canvas
      s.append("<svg viewBox=\"");
      s.append(model.getCanvasDims().getX()).append(" ");
      s.append(model.getCanvasDims().getY()).append(" ");
      s.append(model.getCanvasDims().getWidth()).append(" ");
      s.append(model.getCanvasDims().getHeight()).append("\" ");
      s.append("xmlns=\"http://www.w3.org/2000/svg\">\n\n");

      for (String name : model.getShapes().keySet()) {
        s.append(makeSvgShape(model.getShapeType(name), name, model.getShapeKeyFrames(name),
            ticksPerSecond));
      }
      s.append("</svg>");
      w.append(s);
    } catch (IOException e) {
      throw new RuntimeException("couldn't append to appendable object");
    }

    // Not all Appendable objects can be/need to be closed. I figured instanceof was fine here.
    if (w instanceof Closeable) {
      try {
        ((Closeable) w).close();
      } catch (IOException e) {
        throw new RuntimeException("could not close file");
      }
    }
  }

  /**
   * Generates the svg for a shape with the specified parameters, including the animation tags it
   * needs.
   *
   * @param shapeType      The type of the shape.
   * @param name           The name of the shape.
   * @param keyFrames      The key frames associated with the shape.
   * @param ticksPerSecond The tick rate of the animation.
   * @return A string containing the shape tag enclosing the animation tags for the shape based on
   * its key frames.
   */
  private String makeSvgShape(ShapeType shapeType, String name, NavigableMap<Integer,
      KeyFrame> keyFrames, int ticksPerSecond) {
    if (keyFrames.isEmpty()) {
      return "";
    }
    StringBuilder s = new StringBuilder();
    // Initial shape tag
    s.append("<");
    String shapeStr;
    String xStr;
    String yStr;
    String wStr;
    String hStr;
    if (shapeType.equals(ShapeType.RECTANGLE)) {
      shapeStr = "rect";
    } else if (shapeType.equals(ShapeType.ELLIPSE)) {
      shapeStr = "ellipse";
    } else {
      throw new RuntimeException("unsupported shape type");
    }
    s.append(shapeStr);
    s.append(" id=\"").append(name).append("\" ");
    if (shapeStr.equals("rect")) {
      xStr = "x";
      yStr = "y";
      wStr = "width";
      hStr = "height";
    } else {
      xStr = "cx";
      yStr = "cy";
      wStr = "rx";
      hStr = "ry";
    }

    KeyFrame firstKeyFrame = keyFrames.get(keyFrames.firstKey());
    int x = firstKeyFrame.getPosition().getX();
    int y = firstKeyFrame.getPosition().getY();
    int w = firstKeyFrame.getWidth();
    int h = firstKeyFrame.getHeight();
    Color c = firstKeyFrame.getColor();

    s.append(xStr).append("=\"").append(x).append("\" ").append(yStr).append("=\"")
        .append(y).append("\" ");
    s.append(wStr).append("=\"").append(w).append("\" ").append(hStr).append("=\"")
        .append(h).append("\" ");

    s.append("fill=\"").append(generateColorString(c)).append("\" visibility=\"hidden\" >");
    s.append("\n\n");
    // Animation tags based on keyFrames
    for (Map.Entry<Integer, KeyFrame> e : keyFrames.entrySet()) {
      if (e.getKey().equals(keyFrames.firstKey())) {
        s.append("    <animate attributeType=\"xml\" begin=\"")
            .append(tickToMs(e.getKey(), ticksPerSecond)).append("ms\" ")
            .append("dur=\"1ms\" attributeName=\"visibility\" from=\"hidden\" ")
            .append("to=\"visible\" fill=\"freeze\" />\n");
      }
      else {
        int previousTick = keyFrames.lowerKey(e.getKey());
        KeyFrame previousKeyFrame = keyFrames.get(previousTick);
        s.append(generateAnimationTags(xStr, yStr, wStr, hStr, previousTick, previousKeyFrame,
            e.getKey(), e.getValue(), ticksPerSecond));
      }
    }
    // Close tag
    s.append("\n</").append(shapeStr).append(">\n\n");
    return s.toString();
  }

  private String generateAnimationTags(String xStr, String yStr, String wStr, String hStr,
      int startTick, KeyFrame start, int endTick, KeyFrame end,
      int ticksPerSecond) {
    StringBuilder allTags = new StringBuilder();

    if (start.getPosition().getX() != end.getPosition().getX()) {
      allTags.append(generateAnimationTag(xStr, startTick,
          Integer.toString(start.getPosition().getX()), endTick,
          Integer.toString(end.getPosition().getX()), ticksPerSecond));
    }
    if (start.getPosition().getY() != end.getPosition().getY()) {
      allTags.append(generateAnimationTag(yStr, startTick,
          Integer.toString(start.getPosition().getY()), endTick,
          Integer.toString(end.getPosition().getY()), ticksPerSecond));
    }
    if (start.getWidth() != end.getWidth()) {
      allTags.append(generateAnimationTag(wStr, startTick,
          Integer.toString(start.getWidth()), endTick,
          Integer.toString(end.getWidth()), ticksPerSecond));
    }
    if (start.getHeight() != end.getHeight()) {
      allTags.append(generateAnimationTag(hStr, startTick,
          Integer.toString(start.getHeight()), endTick,
          Integer.toString(end.getHeight()), ticksPerSecond));
    }
    if (!start.getColor().equals(end.getColor())) {
      allTags.append(generateAnimationTag("fill", startTick,
          generateColorString(start.getColor()), endTick,
          generateColorString(end.getColor()), ticksPerSecond));
    }
    return allTags.toString();
  }

  private String generateAnimationTag(String attributeName, int startTick, String startVal,
      int endTick, String endVal, int ticksPerSecond) {
    String base = "    <animate attributeType=\"xml\" " +
        getBeginAndDuration(startTick, endTick, ticksPerSecond) +
        "attributeName=\"";
    String fillFreeze = "fill=\"freeze\" ";
    return base +
        attributeName + "\" " +
        "from=\"" + startVal + "\" " +
        "to=\"" + endVal + "\" " +
        fillFreeze +
        "/>\n";
  }

  /**
   * Computes the time since the beginning of the animation and how long it should lasts, and
   * formats the information using the begin and dur animation tags.
   *
   * @param startTick      The tick at which some movement should begin.
   * @param endTick        The tick at which some movement should end.
   * @param ticksPerSecond The number of ticks in a second.
   * @return A string in the form "begin="{X1}ms" dur ="{X2}ms".
   */
  private String getBeginAndDuration(int startTick, int endTick, int ticksPerSecond) {
    int startMs = tickToMs(startTick, ticksPerSecond);
    int endMs = tickToMs(endTick, ticksPerSecond);
    int durationMs = endMs - startMs;
    return "begin=\"" + startMs + "ms\"" +
        " dur=\"" + durationMs + "ms\" ";
  }

  private int tickToMs(int tick, int ticksPerSecond) {
    int msPerTick = 1000 / ticksPerSecond;
    return msPerTick * tick;
  }

  /**
   * Gets a valid svg representation of a color. E.g. new Color(0, 100, 200) -> rgb(0, 100, 200).
   *
   * @param c The color to translate to a svg-compatible string.
   * @return The color as an svg-compatible string.
   */
  private String generateColorString(Color c) {
    return "rgb(" + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ")";
  }

  @Override
  public void setOutput(Appendable w) {
    this.w = w;
  }

  @Override
  public void modifyAnimationSpeed(int speed) {
    throw new UnsupportedOperationException();
  }
}