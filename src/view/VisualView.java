package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import model.BasicAnimator;
import model.ShapeType;
import util.PanelUtils;

public class VisualView extends JFrame implements IView {

  private BasicAnimator model;
  Panel panel;
  private int speed;



  public VisualView(Panel pane;, int speed) {
    this.panel = panel;
    this.speed = speed;
  }


  @Override
  public double getSpeed() {
    return this.speed;
  }

  @Override
  public void setSpeed(int newSpeed) {
    this.speed = newSpeed;
  }

  @Override
  public String viewType() {
    return "Visual View";
  }

  @Override
  public String getShapeTypeString(ShapeType type) {
    return type.toString();
  }

  @Override
  public void render() {
    this.setTitle("BasicAnimator");
    this.setSize(PanelUtils.panelDimension);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    this.panel = new Panel(model);
    panel.setPreferredSize(PanelUtils.panelDimension);


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


