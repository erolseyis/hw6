package view;

import cs3500.animator.model.ShapeType;

public class SvgView implements IView {

  private double speed;

  public SvgView(double speed) {
    this.speed = speed;
  }

  @Override
  public void runAnimation() {

  }

  @Override
  public double getSpeed() {
    return this.speed;
  }

  public void setSpeed(double newSpeed) {
    this.speed = newSpeed;
  }

  @Override
  public String viewType() {
    return null;
  }

  @Override
  public String getShapeTypeString(ShapeType type) {
    return type.toString();
  }


  public String toString() {
    String svgTxt = "";
    return svgTxt;
  }
}
