package view;

import java.io.IOException;
import javax.naming.OperationNotSupportedException;

import model.Animator;

/**
 * Produces an SVG representation of an animation.
 */
public class SvgView implements IView {

  private Appendable w;

  @Override
  public void render(Animator animation, int ticksPerSecond) {
    try {
      w.append("//TODO");
    } catch (IOException e) {
      throw new RuntimeException("couldn't write to appendable object");
    }
  }


  @Override
  public void setOutput(Appendable w) {
    this.w = w;
  }

  @Override
  public void modifyAnimationSpeed(int speed) throws OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }
}
