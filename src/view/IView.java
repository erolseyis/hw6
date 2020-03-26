package view;


import model.ShapeType;

public interface IView {

  //void runAnimation();

  double getSpeed();

  void setSpeed(int newSpeed);

  String viewType();

  String getShapeTypeString(ShapeType type);

  void render();

}
