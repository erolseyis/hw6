package model;

import java.util.Map;
import javax.naming.OperationNotSupportedException;

/**
 * Class that represent Read Only Model.
 * It is used to retrieve information from the model.
 * Any methods that modify that modify the model throws UnsupportedOperationException.
 */
public class ReadOnlyModel implements Animator {
  private Animator readOnly;

  /**
   * Constructor for ReadOnlyModel to be read by the views.
   *
   * @param readOnly the BasicAnimator model used to retrieve information.
   */
  public ReadOnlyModel(Animator readOnly) {
    this.readOnly = readOnly;
  }



  @Override
  public void addShape(ShapeType type, String name) throws OperationNotSupportedException {
    throw new OperationNotSupportedException("You cannot modify the ReadOnly model");
  }

  @Override
  public void addKeyFrame(String name, int tick, KeyFrame keyFrame)
      throws OperationNotSupportedException {
    throw new OperationNotSupportedException("You cannot modify the ReadOnly model");
  }

  @Override
  public void addMotion(String name, int t1, KeyFrame before, int t2, KeyFrame after)
      throws OperationNotSupportedException {
    throw new OperationNotSupportedException("You cannot modify the ReadOnly model");
  }

  @Override
  public ShapeType getShapeType(String name) {
    return readOnly.getShapeType(name);
  }

  @Override
  public Map<Integer, KeyFrame> getShapeKeyFrames(String name) {
    return readOnly.getShapeKeyFrames(name);
  }

  @Override
  public Map<String, KeyFrame> getShapesAtTick(int tick) {
    return readOnly.getShapesAtTick(tick);
  }

  @Override
  public void removeShape(String name) throws OperationNotSupportedException {
    throw new OperationNotSupportedException("You cannot modify the ReadOnly model");
  }

  @Override
  public void removeMotion(String name, boolean last) throws OperationNotSupportedException {
    throw new OperationNotSupportedException("You cannot modify the ReadOnly model");
  }

  @Override
  public CanvasDims getCanvasDims() {
    return readOnly.getCanvasDims();
  }

  @Override
  public void setCanvasDims(CanvasDims dims) throws OperationNotSupportedException {
    throw new OperationNotSupportedException("You cannot modify the ReadOnly model");
  }
}
