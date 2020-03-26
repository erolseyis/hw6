package view;


import model.ShapeType;

public interface IView {

  //void runAnimation();

  double getSpeed();

  void setSpeed(double newSpeed);

  String viewType();

  String getShapeTypeString(ShapeType type);

  void render();

}
