package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.BasicAnimator;
import view.IView;
import view.VisualView;

public class Controller implements Features, ActionListener {

  private BasicAnimator model;
  private VisualView view;


  public Controller(BasicAnimator model, VisualView view) {
    this.model = model;
    this.view = view;
}


  public void runViewAnimation(BasicAnimator model , int speed, String fn, Appendable a) {
    IView v = new VisualView();
    Timer t = new Timer(speed, new ActionListener() {
      int tick = 0;
      @Override
      public void actionPerformed(ActionEvent e) {
        v.render(model, speed);
        tick++;
      }
    });
    t.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

}
