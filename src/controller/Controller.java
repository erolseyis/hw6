package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Animator;
import view.IView;

public class Controller implements ActionListener {

  private Animator model;
  private IView view;

  public Controller(Animator model, IView view, int speed, Appendable out) {
    this.model = model;
    this.view = view;
    view.setListener(this);
    view.setOutput(out);
    view.render(model, speed);
  }


//  public void runViewAnimation(BasicAnimator model, int speed, String fn, Appendable a) {
//    IView v = new VisualView();
//    Timer t = new Timer(speed, new ActionListener() {
//      int tick = 0;
//
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        v.render(model, speed);
//        tick++;
//      }
//    });
//    t.start();
//  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      // TODO modifying keyframes and model-involved operations
    }
  }


}
