package view;

import java.io.IOException;
import javax.naming.OperationNotSupportedException;

import model.Animator;

/**
 * A textual view of the animation. It's pretty trash because we did not leave enough time to do
 * the assignment.
 */
public class TextView implements IView {

  private Appendable w;

  @Override
  public void render(Animator animation, int speed) {
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
