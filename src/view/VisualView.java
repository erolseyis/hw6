package view;


import javax.swing.JFrame;
import model.BasicAnimator;
import model.ShapeType;

public class VisualView extends JFrame implements IView {

  private BasicAnimator model;
  //private Panel p;
  private int speed;



  public VisualView(Panel p) {
    //this.p = p;
    this.speed = speed;
  }


//  @Override
//  public void runAnimation() {
//
//  }

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

  @Override
  public void render() {

  }


//  public void draw(Graphics g) {
//    Graphics2D g2d = (Graphics2D) g;
//
//    for (int i = 0; i < model.
//        getShapesAtTick(speed).size();
//        i++) {
//      Map mp = model.getShapesAtTick(speed).get(i);
//      //gets the keyframe present at the model
//      KeyFrame kf = model.getKeyFrame();
//
//      // Get all keys
//      Set<String> keys = mp.keySet();
//      for (String k : keys) {
//        if (model.getShape(k) == ShapeType.RECTANGLE) {
//          g.setColor(kf.getColor());
//          g.fillRect((kf.getPositionX()), kf.getPositionY(),
//              kf.getWidth(),  kf.getHeight());
//        }
//
//        else if (model.getShape(k) == ShapeType.ELLIPSE) {
//          g.setColor(kf.getColor());
//          g.fillRect((kf.getPositionX(), kf.getPositionY(),
//              kf.getWidth(),  kf.getHeight());
//        }
//      }
//    }
//  }
}


