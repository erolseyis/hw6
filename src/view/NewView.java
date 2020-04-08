package view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.ReadOnlyModel;


public class NewView extends JFrame implements ItemListener, ActionListener, ChangeListener {

  private ReadOnlyModel model;
  private double speed;
  private AnimationPanel animationPanel;
  private boolean looped;
  private Timer timer;

  public NewView() {
    this.setTitle("Animation!");
    this.setSize(1200,1000);
    setLocation(500, 10);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.model = model;
    this.speed = speed;
    this.looped = false;
    this.timer = new Timer( (int)((1.0 / speed) * 1000), this);





  }



  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void itemStateChanged(ItemEvent e) {

  }

  @Override
  public void stateChanged(ChangeEvent e) {

  }

  public double getSpeed() {
    return speed;
  }


  public void setSpeed(double newSpeed) {
    if (newSpeed >= 1) {
      this.timer.setDelay((int) ((1000 / newSpeed)));
      this.speed = newSpeed;
    }
  }

  public boolean getLoop() {
    return looped;
  }

  public void changeLoop() {
    if (this.looped) {
      this.looped = false;
    } else {
      this.looped = true;
    }
  }


}
