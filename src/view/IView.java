package view;

import cs3500.animator.model.ShapeType;

public interface IView {

  void runAnimation();

  double getSpeed();

  void setSpeed(double newSpeed);

  String viewType();

  String getShapeTypeString(ShapeType type);

  //IView render();

}
