package view;


import javax.naming.OperationNotSupportedException;

import model.Animator;

public interface IView {

  void render(Animator model, int ticksPerSecond);

  void setOutput(Appendable w);

  void modifyAnimationSpeed(int speed);
}
