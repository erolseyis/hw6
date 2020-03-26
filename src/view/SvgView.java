package view;


import model.ShapeType;

public class SvgView implements IView {

  private double speed;

  public SvgView(int speed) {
    this.speed = speed;
  }

//  @Override
//  public void runAnimation() {
//
//  }

  @Override
  public double getSpeed() {
    return this.speed;
  }

  public void setSpeed(int newSpeed) {
    this.speed = newSpeed;
  }

  @Override
  public String viewType() {
    return "SVG View";
  }

  @Override
  public String getShapeTypeString(ShapeType type) {
    return type.toString();
  }

  @Override
  public void render() {

  }


  public String toString() {
    String svgTxt = "";
    return svgTxt;
  }
}
