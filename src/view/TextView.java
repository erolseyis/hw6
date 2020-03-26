//package view;
//
//
//import java.util.Map;
//import javax.swing.JFrame;
//import model.BasicAnimator;
//import model.KeyFrame;
//import model.ShapeType;
//
//public class TextView extends JFrame implements IView {
//
//  private BasicAnimator model;
//  private double speed;
//
//
//  public TextView(BasicAnimator model, double speed) {
//    this.model = model;
//    this.speed = speed;
//  }
//
//
//
//  public double getSpeed() {
//    return speed;
//  }
//
//  @Override
//  public void setSpeed(int newSpeed) {
//    this.speed = newSpeed;
//  }
//
//  @Override
//  public String viewType() {
//    return null;
//  }
//
//  @Override
//  public String getShapeTypeString(ShapeType type) {
//    if (type == ShapeType.ELLIPSE) {
//      return ShapeType.ELLIPSE.name;
//    }
//    else {
//      return ShapeType.RECTANGLE.name;
//    }
//  }
//
//  @Override
//  public void render() {
//
//  }
//
//  public String viewTextDisplay() {
//
//    StringBuilder output = new StringBuilder();
//    for (String name : model.shapes.keySet()) {
//      output.append("shape " + name + " " + model.shapes.get(name).toString() + "\n");
//      for (Map.Entry<Integer, KeyFrame> entry : model.shapeTimelines.get(name).entrySet()) {
//        if (!entry.getKey().equals(model.shapeTimelines.get(name).firstKey())) {
//          output.append("motion " + name + "\n");
//          // This "start" bit is cumbersome and unnecessary but matches the desired format.
//          output.append("start:\t");
//          output.append("Tick: " + model.shapeTimelines.get(name).lowerKey(entry.getKey()));
//          output.append(model.shapeTimelines.get(name).lowerEntry(entry.getKey()).getValue().toString());
//          output.append("\n");
//          output.append("end:\t");
//          output.append("Tick: " + entry.getKey());
//          output.append(entry.getValue().toString());
//          output.append("\n");
//        }
//      }
//    }
//    return output.toString();
//  }
//}
