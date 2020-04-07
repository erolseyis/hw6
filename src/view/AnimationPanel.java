package view;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

import model.Animator;
import model.KeyFrame;
import model.ShapeType;

import java.awt.*;
import java.util.Map;


/**
 * This panel represents the region where the
 * lines of the turtle must be drawn.
 * <p>
 * If one has to create a container that makes
 * custom drawing, the conventional way is to
 * create a class that extends JPanel or JLabel
 */
public class AnimationPanel extends JPanel {
  private Animator model;
  int xOffset;
  int yOffset;
  private int tick;
  private int ticksPerSecond;
  Timer timer;

  public AnimationPanel(Animator model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("cannot create animation panel with no animation model");
    }
    this.model = model;
    this.setBackground(Color.BLACK);
    this.tick = 1;
    this.xOffset = this.model.getCanvasDims().getX();
    this.yOffset = this.model.getCanvasDims().getY();
  }

  public void play() {
    timer.start();
  }

  public void pause() {
    timer.stop();
  }

  /**
   * Sets the tick rate of the animation and refreshes the panel to make the change take hold.
   * @param ticksPerSecond The new tick rate.
   */
  public void setTicksPerSecond(int ticksPerSecond) {
    this.ticksPerSecond = ticksPerSecond;
    // 1/ticks per second * 1000 = ms per tick
    timer = new Timer(1000 / ticksPerSecond, e -> {
      repaint();
      System.out.println(tick);
      tick++;
    });
  }

  public void setModel(Animator model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (Map.Entry<String, KeyFrame> shape:
            this.model.getShapesAtTick(this.tick).entrySet()) {
      g2d.setColor(shape.getValue().getColor());
      System.out.println(shape.getKey());
      if (this.model.getShapeType(shape.getKey()).equals(ShapeType.RECTANGLE)) {
        g2d.fillRect(shape.getValue().getPosition().getX() - xOffset,
                shape.getValue().getPosition().getY() - yOffset,
                shape.getValue().getWidth(), shape.getValue().getHeight());
      } else if (this.model.getShapeType(shape.getKey()).equals(ShapeType.ELLIPSE)) {
        g2d.fillOval(shape.getValue().getPosition().getX() - xOffset,
                shape.getValue().getPosition().getY() - yOffset,
                shape.getValue().getWidth(), shape.getValue().getHeight());
      } else {
        throw new RuntimeException("unexpected shape type");
      }
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(this.model.getCanvasDims().getWidth(),
            this.model.getCanvasDims().getHeight());
  }
}