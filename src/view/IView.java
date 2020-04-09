package view;


import javax.naming.OperationNotSupportedException;

import model.Animator;

/**
 * View interface for defining similar behavior for different types of views. Used to display
 * Model.
 */
public interface IView {

  /**
   * Renders this IView's model.
   */
  void render(Animator model, int ticksPerSecond);

  /**
   * Changes the filepath to be written.
   */
  void setOutput(Appendable w);

  /**
   * Changes animations speed to the given speed.
   *
   * @throws UnsupportedOperationException if this view is TextView or VisualView.
   */
  void modifyAnimationSpeed(int speed) throws OperationNotSupportedException;
}