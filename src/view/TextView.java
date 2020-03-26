package view;


import javax.swing.JFrame;
import model.BasicAnimator;
import model.ShapeType;

public class TextView extends JFrame implements IView {

  private BasicAnimator model;
  private double speed;


  public TextView(BasicAnimator model, double speed) {
    this.model = model;
    this.speed = speed;
  }

  @Override
  public void runAnimation() {

  }


  public double getSpeed() {
    return speed;
  }

  @Override
  public void setSpeed(double newSpeed) {
    this.speed = newSpeed;
  }

  @Override
  public String viewType() {
    return null;
  }

  @Override
  public String getShapeTypeString(ShapeType type) {
    if (type == ShapeType.ELLIPSE) {
      return ShapeType.ELLIPSE.name;
    }
    else {
      return ShapeType.RECTANGLE.name;
    }
  }


}
