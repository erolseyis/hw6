package view;


import javax.naming.OperationNotSupportedException;

import model.Animator;

public interface IView {

  void render(Animator animation, int ticksPerSecond);

  void setOutput(Appendable w) throws OperationNotSupportedException;

  void modifyAnimationSpeed(int speed) throws OperationNotSupportedException;

//  /**
//   * Change the speed of the animation by user.
//   *
//   * @param speed how many ticks per second, higher tempo means higher speed
//   */
//  void setSpeed(double speed);
}
