package view;

import java.awt.event.ActionListener;
import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

import model.Animator;
import model.KeyFrame;
import model.ShapeType;

/**
 * Handles creation of a textual representation of an animation.
 */
public class TextView implements IView {

  private Appendable w;

  @Override
  public void render(Animator model, int speed) {
    try {
      StringBuilder s = new StringBuilder();
      // Canvas dimensions
      s.append("canvas ");
      s.append(model.getCanvasDims().getX());
      s.append(" ");
      s.append(model.getCanvasDims().getY());
      s.append(" ");
      s.append(model.getCanvasDims().getWidth());
      s.append(" ");
      s.append(model.getCanvasDims().getHeight());
      s.append("\n");
      // Each shape declaration...
      for (String name : model.getShapes().keySet()) {
        s.append("shape ");
        s.append(name);
        s.append(" ");
        if (model.getShapeType(name).equals(ShapeType.ELLIPSE)) {
          s.append("rectangle");
        } else if (model.getShapeType(name).equals(ShapeType.RECTANGLE)) {
          s.append("ellipse");
        } else {
          throw new RuntimeException("unsupported shape type");
        }
        s.append("\n");
        // ... followed by each of the motions for that shape
        for (Map.Entry<Integer, KeyFrame> entry : model.getShapeKeyFrames(name).entrySet()) {
          if (!entry.getKey().equals(model.getShapeKeyFrames(name).firstKey())) {
            s.append("motion ");
            s.append(name);
            s.append(" ");
            s.append(model.getShapeKeyFrames(name).lowerKey(entry.getKey()));
            s.append(" ");
            s.append(model.getShapeKeyFrames(name).lowerEntry(entry.getKey())
                    .getValue().getPosition().getX());
            s.append(" ");
            s.append(model.getShapeKeyFrames(name).lowerEntry(entry.getKey())
                    .getValue().getPosition().getX());
            s.append(" ");
            s.append(model.getShapeKeyFrames(name).lowerEntry(entry.getKey())
                    .getValue().getWidth());
            s.append(" ");
            s.append(model.getShapeKeyFrames(name).lowerEntry(entry.getKey())
                    .getValue().getHeight());
            s.append(" ");
            s.append(model.getShapeKeyFrames(name).lowerEntry(entry.getKey())
                    .getValue().getColor().getRed());
            s.append(" ");
            s.append(model.getShapeKeyFrames(name).lowerEntry(entry.getKey())
                    .getValue().getColor().getGreen());
            s.append(" ");
            s.append(model.getShapeKeyFrames(name).lowerEntry(entry.getKey())
                    .getValue().getColor().getBlue());
            s.append(" ");
            s.append(entry.getKey());
            s.append(" ");
            s.append(entry.getValue().getPosition().getX());
            s.append(" ");
            s.append(entry.getValue().getPosition().getY());
            s.append(" ");
            s.append(entry.getValue().getWidth());
            s.append(" ");
            s.append(entry.getValue().getHeight());
            s.append(" ");
            s.append(entry.getValue().getColor().getRed());
            s.append(" ");
            s.append(entry.getValue().getColor().getGreen());
            s.append(" ");
            s.append(entry.getValue().getColor().getBlue());
            s.append("\n");
          }
        }
      }
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

  @Override
  public void setOutput(Appendable w) {
    this.w = w;
  }

  @Override
  public void setListener(ActionListener listener) {
    // doing nothing isn't the best option but we didn't have time to implement the strategy
    // pattern or anything that would've made this unnecessary while allowing the controller to
    // work on any views
  }
}
