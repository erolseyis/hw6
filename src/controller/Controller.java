package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.BasicAnimator;
import view.EditorView;

public class Controller implements Features, ActionListener {

  private BasicAnimator model;
  private EditorView view;

  boolean paused = true;

  private Timer timer;
  private int t = 0;
  private double tickRate = 1.0;

  public Controller(BasicAnimator model, EditorView view) {
    this.model = model;
    this.view = view;


    int tickRateMS = ((Double) (1000 / tickRate))
        .intValue();
    timer = new Timer(tickRateMS, this);
    timer.setActionCommand("timer");
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
      case "Start":
        this.paused = false;
        if () {
          view.render();
          hasStart = true;
        } else {
          view.restart();
        }
        timer.start();
        break;
      case "Pause/Resume":
        this.paused = true;
        timer.stop();
        //view.pauseOrResumerPanel();
        break;
      case "Restart":
        t = 0;
        timer.restart();
        break;
      case "Loop":
        view.changeLoop();
        break;
      case "Increase the speed":
        view.setSpeed(view.getSpeed() + 1);
        break;
      case "Decrease the speed":
        view.setSpeed(view.getSpeed() - 1);
        break;
      default: throw new IllegalArgumentException("Invalid Command");
    }
  }


}
