package model;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import util.AnimationBuilder;
import util.MathUtils;

/**
 * Models an animation that only uses ellipses and rectangles.
 */
public class BasicAnimator implements Animator {

  // The named shapes that have been created
  private Map<String, ShapeType> shapes;

  // Maps the names of the shapes to their states at each specified KeyFrame
  private Map<String, NavigableMap<Integer, KeyFrame>> shapeTimelines;

  // Bounding box dimensions
  private CanvasDims dims;

  /**
   * Constructs a BasicAnimator.
   */
  public BasicAnimator() {
    this.shapes = new LinkedHashMap<>();
    this.shapeTimelines = new LinkedHashMap<>();
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
  public void addMotion(String name, int t1, KeyFrame start, int t2, KeyFrame end) {
    if (name == null) {
      throw new IllegalArgumentException("name cannot be null");
    }
    if (start == null || end == null) {
      throw new IllegalArgumentException("cannot add null keyframes");
    }
    if (t2 < t1) {
      throw new IllegalArgumentException("motion cannot end at a smaller time than its start");
    }

    NavigableMap<Integer, KeyFrame> shapeKeyFrames = this.getShapeKeyFrames(name);

    if (!shapeKeyFrames.isEmpty()) {
      int timelineEnd = shapeKeyFrames.lastKey();
      int timelineStart = shapeKeyFrames.firstKey();

      // A motion that starts before the existent timeline and ends at the timeline's start
      if (t1 < timelineStart) {
        if (!(t2 == timelineStart && shapeKeyFrames.get(timelineStart).equals(end))) {
          throw new IllegalArgumentException("motion inconsistent with shape timeline");
        }
        this.addKeyFrame(name, t1, start);
      } else { // A motion that starts at the end of the existent timeline and ends beyond it
        if (!(t1 == timelineEnd && shapeKeyFrames.get(timelineEnd).equals(start))) {
          throw new IllegalArgumentException("motion inconsistent with shape timeline");
        }
        this.addKeyFrame(name, t2, end);
      }
    } else { // A motion can always be added to an empty timeline
      this.addKeyFrame(name, t1, start);
      if (t1 != t2) {
        this.addKeyFrame(name, t2, end);
      }
    }
  }

  @Override
  public ShapeType getShapeType(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name cannot be null");
    }

    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("shape with given name does not exist");
    }

    return this.shapes.get(name);
  }

  @Override
  public NavigableMap<Integer, KeyFrame> getShapeKeyFrames(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name cannot be null");
    }

    if (!this.shapeTimelines.containsKey(name)) {
      throw new IllegalArgumentException("shape with given name does not exist");
    }

    return this.shapeTimelines.get(name);
  }

  @Override
  public Map<String, KeyFrame> getShapesAtTick(int tick) {
    if (tick < 1) {
      throw new IllegalArgumentException("tick must be positive integer");
    }
    Map<String, KeyFrame> shapeKeyFrames = new LinkedHashMap<>();
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
        int lastRed = kf1.getColor().getRed();
        int nextRed = kf2.getColor().getRed();
        int lastGreen = kf1.getColor().getGreen();
        int nextGreen = kf2.getColor().getGreen();
        int lastBlue = kf1.getColor().getBlue();
        int nextBlue = kf2.getColor().getBlue();
        int lastH = kf1.getHeight();
        int nextH = kf2.getHeight();
        int lastW = kf1.getWidth();
        int nextW = kf2.getWidth();
        int lastX = kf1.getPosition().getX();
        int nextX = kf2.getPosition().getX();
        int lastY = kf1.getPosition().getY();
        int nextY = kf2.getPosition().getY();
        int newRed = new MathUtils().interpolate(t1, lastRed, t2, nextRed, tick);
        int newGreen = new MathUtils().interpolate(t1, lastGreen, t2, nextGreen, tick);
        int newBlue = new MathUtils().interpolate(t1, lastBlue, t2, nextBlue, tick);
        Color newColor = new Color(newRed, newGreen, newBlue);
        int newH = new MathUtils().interpolate(t1, lastH, t2, nextH, tick);
        int newW = new MathUtils().interpolate(t1, lastW, t2, nextW, tick);
        int newX = new MathUtils().interpolate(t1, lastX, t2, nextX, tick);
        int newY = new MathUtils().interpolate(t1, lastY, t2, nextY, tick);
        Position2D newPosition = new Position2D(newX, newY);
        shapeKeyFrames.put(entry.getKey(), new KeyFrame(newColor, newW, newH, newPosition));
      }
      // The shape has been given no more directions but should still appear where it was
      if (previousState != null && nextState == null) {
        shapeKeyFrames.put(entry.getKey(), previousState.getValue());
      }
    }
    return shapeKeyFrames;
  }

  @Override
  public void removeShape(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name cannot be null");
    }

    if (!this.shapeTimelines.containsKey(name)) {
      throw new IllegalArgumentException("shape with given name does not exist");
    }

    this.shapeTimelines.remove(name);
    this.shapes.remove(name);
  }

  @Override
  public void removeMotion(String name, boolean last) {
    if (name == null) {
      throw new IllegalArgumentException("name cannot be null");
    }

    if (!this.shapeTimelines.containsKey(name)) {
      throw new IllegalArgumentException("shape with given name does not exist");
    }

    int numKeyFrames = this.shapeTimelines.get(name).size();

    // 0 or 1 KeyFrames does not constitute a motion
    if (this.shapeTimelines.get(name).isEmpty() || numKeyFrames == 1) {
      throw new IllegalArgumentException("no motions to remove");
    }
    // 2 KeyFrames is a single motion, so remove both
    if (numKeyFrames == 2) {
      shapeTimelines.get(name).clear();
    } else { // Remove the first or last KeyFrame
      int key;
      if (last) {
        key = shapeTimelines.get(name).lastKey();
      } else {
        key = shapeTimelines.get(name).firstKey();
      }
      shapeTimelines.get(name).remove(key);
    }
  }

  @Override
  public CanvasDims getCanvasDims() {
    return this.dims;
  }

  @Override
  public void setCanvasDims(CanvasDims dims) {
    this.dims = dims;
  }

  @Override
  public Map<String, ShapeType> getShapes() {
    return this.shapes;
  }

  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    for (String name : this.shapes.keySet()) {
      output.append("shape " + name + " " + this.shapes.get(name).toString() + "\n");
      for (Map.Entry<Integer, KeyFrame> entry : this.shapeTimelines.get(name).entrySet()) {
        if (!entry.getKey().equals(this.shapeTimelines.get(name).firstKey())) {
          output.append("motion " + name + "\n");
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


  /**
   * The builder class for a BasicAnimator.
   */
  public static final class BasicAnimationBuilder implements AnimationBuilder<Animator> {

    private Animator animator;

    public BasicAnimationBuilder() {
      this.animator = new BasicAnimator();
    }

    @Override
    public Animator build() {
      return animator;
    }

    @Override
    public AnimationBuilder<Animator> setBounds(int x, int y, int width, int height) {
      animator.setCanvasDims(new CanvasDims(x, y, width, height));
      return this;
    }

    @Override
    public AnimationBuilder<Animator> declareShape(String name, String type) {
      if (type.equals("rectangle")) {
        animator.addShape(ShapeType.RECTANGLE, name);
      } else if (type.equals("ellipse")) {
        animator.addShape(ShapeType.ELLIPSE, name);
      } else {
        throw new IllegalArgumentException("unsupported shape type");
      }
      return this;
    }

    @Override
    public AnimationBuilder<Animator> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
                                                int r1, int g1, int b1, int t2, int x2, int y2,
                                                int w2, int h2, int r2, int g2, int b2) {
      animator.addMotion(name, t1, new KeyFrame(new Color(r1, g1, b1), w1, h1, new Position2D(x1, y1)),
              t2, new KeyFrame(new Color(r2, g2, b2), w2, h2, new Position2D(x2, y2)));
      return this;
    }

    @Override
    public AnimationBuilder<Animator> addKeyframe(String name, int t, int x, int y, int w, int h,
                                                  int r, int g, int b) {
      animator.addKeyFrame(name, t, new KeyFrame(new Color(r, g, b), w, h, new Position2D(x, y)));
      return this;
    }
  }
}
