package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.ReadOnlyModel;
import util.PanelUtils;


public class NewView extends JFrame implements ItemListener, ActionListener, ChangeListener {

  private ReadOnlyModel model;
  private double speed;
  private AnimationPanel animationPanel;
  private boolean looped;
  private Timer timer;


  //Buttons
  private JButton startButton;
  private JButton pauseResumeButton;
  private JButton restartButton;
  private JButton loopButton;
  private JButton speedUpButton;
  private JButton SpeedDownButton;


  public NewView() {
    this.setTitle("Animation!");
    this.setSize(1200, 1000);
    setLocation(500, 10);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.model = model;
    this.speed = speed;
    this.looped = false;
    this.timer = new Timer((int) ((1.0 / speed) * 1000), this);

    animationPanel = new AnimationPanel(model);
    animationPanel.setPreferredSize(PanelUtils.panelDimension);

    //Buttons
    JPanel buttonSet = new JPanel();
    buttonSet.setPreferredSize(PanelUtils.buttonDimension);
    this.add(buttonSet, BorderLayout.SOUTH);

    //Start Button
    startButton = new JButton("Start");
    startButton.setMargin(PanelUtils.BUTTON_INTSET);
    startButton.setFont(PanelUtils.BUTTON_FONT);
    startButton.setActionCommand("Start Animation");
    startButton.setPreferredSize(PanelUtils.buttonDimension);
    buttonSet.add(startButton);

    //Scroll Bar
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(50, 50));


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
