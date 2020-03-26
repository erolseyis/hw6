package view;

import cs3500.animator.model.ShapeType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

public class Panel extends JPanel {

  private List<Shape> shapes;


  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D gfx = (Graphics2D) g;

    g2d.setColor(Color.BLACK);

    AffineTransform originalTransform = gfx.getTransform();

    for (Shapes s : shapes) {
      if (s.getRender()) {
        Posn p = s.getPosn();
        int x = (int) p.getX();
        int y = (int) p.getY();
        int d1 = (int) s.getD1();
        int d2 = (int) s.getD2();
        Color c = s.getColor();
        if (s.getShapeType().equals(ShapeType.OVAL)) {
          g2d.setColor(c);
          g2d.fillOval(x, y, d1 * 2, d2 * 2);
          g2d.drawOval(x, y, d1 * 2, d2 * 2);
        } else if (s.getShapeType().equals(ShapeType.RECTANGLE)) {
          g2d.setColor(c);
          g2d.fillRect(x, y, d1, d2);
          g2d.drawRect(x, y, d1, d2);
        }
      }
    }

    g2d.setTransform(originalTransform);
  }


}
}

