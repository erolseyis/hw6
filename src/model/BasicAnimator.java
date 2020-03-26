package model;

import Utilities.MathUtils;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Represents an animator that only uses ellipses and rectangles.
 */
public class BasicAnimator implements Animator {
  // The named shapes that have been created
  public Map<String, ShapeType> shapes;
  // Maps the names of the shapes to their states at each specified KeyFrame
  public Map<String, NavigableMap<Integer, KeyFrame>> shapeTimelines;

  /**
   * Constructs a BasicAnimator.
   */
  public BasicAnimator() {
    this.shapes = new HashMap<>();
    this.shapeTimelines = new HashMap<>();
  }

  @Override
  public void addShape(ShapeType type, String name) {
    if (type == null) {
      throw new IllegalArgumentException("shape type cannot be null");
    }

    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("name cannot be null or the empty string");
    }

    if (this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("shape with given name already exists");
    }

    this.shapes.put(name, type);
    this.shapeTimelines.put(name, new ConcurrentSkipListMap<>());
  }

  @Override
  public void addKeyFrame(String name, int tick, KeyFrame keyFrame) {
    if (name == null) {
      throw new IllegalArgumentException("name cannot be null");
    }

    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("shape with given name does not exist");
    }

    if (tick < 1) {
      throw new IllegalArgumentException("tick must be positive integer");
    }

    if (this.shapeTimelines.get(name).containsKey(tick)) {
      throw new IllegalArgumentException("cannot add two keyframes at the same tick");
    }

    this.shapeTimelines.get(name).put(tick, keyFrame);
  }

  @Override
  public ShapeType getShape(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name cannot be null");
    }

    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("shape with given name does not exist");
    }

    return this.shapes.get(name);
  }

  @Override
  public Map<String, KeyFrame> getShapesAtTick(int tick) {
    if (tick < 1) {
      throw new IllegalArgumentException("tick must be positive integer");
    }
    Map<String, KeyFrame> shapeKeyFrames = new HashMap<>();
    for (Map.Entry<String, NavigableMap<Integer, KeyFrame>> entry : this.shapeTimelines.entrySet()) {
      Map.Entry<Integer, KeyFrame> previousState = entry.getValue().floorEntry(tick);
      Map.Entry<Integer, KeyFrame> nextState = entry.getValue().ceilingEntry(tick);
      // If either is null, the tick is either before the shape appears or after its last given kf
      if (previousState != null && nextState != null) {
        int t1 = previousState.getKey();
        int t2 = nextState.getKey();
        KeyFrame kf1 = previousState.getValue();
        if (t2 == t1) { // This should save us from dealing with a div by 0 error later
          shapeKeyFrames.put(entry.getKey(), kf1);
          continue;
        }
        KeyFrame kf2 = nextState.getValue();
        int lastRed = kf1.color.getRed();
        int nextRed = kf2.color.getRed();
        int lastGreen = kf1.color.getGreen();
        int nextGreen = kf2.color.getGreen();
        int lastBlue = kf1.color.getBlue();
        int nextBlue = kf2.color.getBlue();
        int lastH = kf1.h;
        int nextH = kf2.h;
        int lastW = kf1.w;
        int nextW = kf2.w;
        int lastX = kf1.position.getX();
        int nextX = kf2.position.getX();
        int lastY = kf1.position.getY();
        int nextY = kf2.position.getY();
        int newRed = new MathUtils().interpolate(t1, lastRed, t2, nextRed, tick);
        int newGreen = new MathUtils().interpolate(t1, lastGreen, t2, nextGreen, tick);
        int newBlue = new MathUtils().interpolate(t1, lastBlue, t2, nextBlue, tick);
        Color newColor = new Color(newRed, newGreen, newBlue);
        int newH = new MathUtils().interpolate(t1, lastH, t2, nextH, tick);
        int newW = new MathUtils().interpolate(t1, lastW, t2, nextW, tick);
        int newX = new MathUtils().interpolate(t1, lastX, t2, nextX, tick);
        int newY = new MathUtils().interpolate(t1, lastY, t2, nextY, tick);
        Position newPosition = new Position(newX, newY);
        shapeKeyFrames.put(entry.getKey(), new KeyFrame(newColor, newW, newH, newPosition));
      }
    }
    return shapeKeyFrames;
  }

  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    for (String name : this.shapes.keySet()) {
      output.append("shape " + name + " " + this.shapes.get(name).toString() + "\n");
      for (Map.Entry<Integer, KeyFrame> entry : this.shapeTimelines.get(name).entrySet()) {
        if (!entry.getKey().equals(this.shapeTimelines.get(name).firstKey())) {
          output.append("motion " + name + "\n");
          // This "start" bit is cumbersome and unnecessary but matches the desired format.
          output.append("start:\t");
          output.append("Tick: " + this.shapeTimelines.get(name).lowerKey(entry.getKey()));
          output.append(this.shapeTimelines.get(name).lowerEntry(entry.getKey()).getValue().toString());
          output.append("\n");
          output.append("end:\t");
          output.append("Tick: " + entry.getKey());
          output.append(entry.getValue().toString());
          output.append("\n");
        }
      }
    }
    return output.toString();
  }

  // A more complex animator could implement convenience methods such as "doNothing" that finds
  // the last KeyFrame and adds a KeyFrame with the same state at a tick which is necessarily
  // larger than the last KeyFrame's tick, essentially making the shape not change until the
  // given tick.
}
