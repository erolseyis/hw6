package view;



import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import model.Animator;

/**
 * Visual View class for drawing an animation in a window.
 */
public class VisualView extends JFrame implements IView {
  private AnimationPanel animationPanel;

  /**
   * Constructs a VisualView.
   */
  public VisualView() {
    super();
    this.setTitle("Animation");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.animationPanel = null;
    this.setBackground(Color.WHITE);
  }

  @Override
  public void render(Animator model, int ticksPerSecond) {
    this.animationPanel = new AnimationPanel(model);
    this.modifyAnimationSpeed(ticksPerSecond);
    this.add(new JScrollPane(animationPanel));
    this.setPreferredSize(this.animationPanel.getPreferredSize());
    this.pack();
    this.animationPanel.play();
    setVisible(true);
  }

  @Override
  public void modifyAnimationSpeed(int speed) {
    if (this.animationPanel == null) {
      throw new IllegalArgumentException("Cannot modify animation speed while not rendering an "
          + "animation.");
    }
    this.animationPanel.setTicksPerSecond(speed);
  }

  @Override
  public void setOutput(Appendable w) {
    throw new UnsupportedOperationException("Operation not supported");
  }
}