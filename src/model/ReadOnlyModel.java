package model;

import java.util.Map;
import java.util.NavigableMap;

/**
 * An immutable model of an animation to be read by the views, ensuring they never directly
 * manipulate the model.
 */
public class ReadOnlyModel implements Animator {

  private Animator model;

  public ReadOnlyModel(Animator model) {

    this.model = model;
  }


  @Override
  public void addShape(ShapeType type, String name) {
    throw new UnsupportedOperationException("You cannot modify the ReadOnly model");
  }

  @Override
  public void addKeyFrame(String name, int tick, KeyFrame keyFrame) {
    throw new UnsupportedOperationException("You cannot modify the ReadOnly model");
  }

  @Override
  public void addMotion(String name, int t1, KeyFrame before, int t2, KeyFrame after) {
    throw new UnsupportedOperationException("You cannot modify the ReadOnly model");
  }

  @Override
  public ShapeType getShapeType(String name) {
    return model.getShapeType(name);
  }

  @Override
  public NavigableMap<Integer, KeyFrame> getShapeKeyFrames(String name) {
    return model.getShapeKeyFrames(name);
  }

  @Override
  public Map<String, KeyFrame> getShapesAtTick(int tick) {
    return model.getShapesAtTick(tick);
  }

  @Override
  public void removeShape(String name) {
    throw new UnsupportedOperationException("You cannot modify the ReadOnly model");
  }

  @Override
  public void removeMotion(String name, boolean last) {
    throw new UnsupportedOperationException("You cannot modify the ReadOnly model");
  }

  @Override
  public CanvasDims getCanvasDims() {
    return model.getCanvasDims();
  }

  @Override
  public void setCanvasDims(CanvasDims dims) {
    throw new UnsupportedOperationException("You cannot modify the ReadOnly model");
  }

  @Override
  public Map<String, ShapeType> getShapes() {
    return model.getShapes();
  }
}