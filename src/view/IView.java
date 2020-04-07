package view;


import javax.naming.OperationNotSupportedException;

import model.Animator;

public interface IView {

  void render(Animator animation, int ticksPerSecond);

  void setOutput(Appendable w) throws OperationNotSupportedException;

  void modifyAnimationSpeed(int speed) throws OperationNotSupportedException;
}
