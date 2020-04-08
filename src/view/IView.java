package view;


import javax.naming.OperationNotSupportedException;

import model.Animator;

/**
 * View interface for defining similar behavior for different types of views.
 * Used to display Model.
 */
public interface IView {

  void render(Animator model, int ticksPerSecond);

  void setOutput(Appendable w);

  void modifyAnimationSpeed(int speed) throws OperationNotSupportedException;
}