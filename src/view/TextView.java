package view;

import cs3500.animator.model.Animator;
import cs3500.animator.model.ShapeType;
import javax.swing.JFrame;

public class TextView extends JFrame implements IView {

  private Animator model;
  private double speed;


  public TextView(Animator model, double speed) {
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

  public String getShapeTypeString(ShapeType type) {
    return type.toString();
  }


}
