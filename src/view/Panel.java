package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;
import model.BasicAnimator;
import model.KeyFrame;
import model.ShapeType;

public class Panel extends JPanel {

    private BasicAnimator model;
    private int time = 0;

    /**
     * Constructor.
     * @param model use this model to construct this AnimatorPanel
     */
    public Panel(BasicAnimator model) {
      this.model = model;

      this.setLayout(new BorderLayout());

    }



  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    // needs zach's help
    for (int i = 0; i < model.getShapesAtTick(time).size();
        i++) {
      Map mp = model.getShapesAtTick(time).get(i);
      //gets the keyframe present at the model
      KeyFrame kf = model.getKeyFrame();

      // Get all keys
      Set<String> keys = mp.keySet();
      for (String k : keys) {
        if (model.getShape(k) == ShapeType.RECTANGLE) {
          g.setColor(kf.getColor());
          g.fillRect((kf.getPositionX()), kf.getPositionY(),
              kf.getWidth(),  kf.getHeight());
        }

        else if (model.getShape(k) == ShapeType.ELLIPSE) {
          g.setColor(kf.getColor());
          g.fillRect((kf.getPositionX(), kf.getPositionY(),
              kf.getWidth(), kf.getHeight());
        }
      }
    }
  }
}

