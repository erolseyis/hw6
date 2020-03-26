package view;

import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.KeyFrame;
import cs3500.animator.model.ShapeType;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;

public class VisualView extends JFrame implements IView {

  private BasicAnimator model;
  private Panel p;
  private double speed;


  public VisualView(Panel p) {
    this.p = p;
    this.speed = speed;
  }


  @Override
  public void runAnimation() {

  }

  @Override
  public double getSpeed() {
    return 0;
  }

  @Override
  public void setSpeed(double newSpeed) {

  }

  @Override
  public String viewType() {
    return null;
  }

  @Override
  public String getShapeTypeString(ShapeType type) {
    return null;
  }


  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    for (int i = 0; i < model.getShapesAtTick(speed).size();
        i++) {
      Map mp = model.getShapesAtTick(speed).get(i);
      //gets the keyframe present at the model
      KeyFrame kf = model.getKeyFrame();

      // Get all keys
      Set<String> keys = mp.keySet();
      for (String k : keys) {
        if (model.getShape(k) == ShapeType.RECTANGLE) {
          g.setColor(kf.getColor);
          g.fillRect((kf.getPosX(), kf.getPosY(),
              kf.getWidth(),  kf.getHeight());
        }

        else if (model.getShape(k) == ShapeType.ELLIPSE)) {
          g.setColor(kf.getColor);
          g.fillRect((kf.getPosX(), kf.getPosY(),
              kf.getWidth(),  kf.getHeight());
        }
      }
    }
  }
}
}

