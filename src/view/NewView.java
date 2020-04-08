package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.JButton;
import javax.swing.JCheckBox;
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
  private JButton speedUpButton;
  private JButton speedDownButton;
  private final JCheckBox loopButton = new JCheckBox("loop");



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
    animationPanel.setPreferredSize(PanelUtils.PANEL_DIMENSION);

    //Buttons
    JPanel buttonSet = new JPanel();
    buttonSet.setPreferredSize(PanelUtils.BUTTON_DIMENSION);
    this.add(buttonSet, BorderLayout.SOUTH);

    //Start Button
    startButton = new JButton("Start");
    startButton.setMargin(PanelUtils.BUTTON_INTSET);
    startButton.setFont(PanelUtils.BUTTON_FONT);
    startButton.setActionCommand("Start");
    startButton.setPreferredSize(PanelUtils.BUTTON_DIMENSION);
    buttonSet.add(startButton);

    //Scroll Bar
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(50, 50));


    pauseResumeButton = new JButton("Pause/Resume");
    pauseResumeButton.setMargin(PanelUtils.BUTTON_INTSET);
    pauseResumeButton.setFont(PanelUtils.BUTTON_FONT);
    pauseResumeButton.setActionCommand("Pause or Resume");
    pauseResumeButton.setPreferredSize(PanelUtils.BUTTON_DIMENSION);
    buttonSet.add(pauseResumeButton);





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

  /**
   * Get the speed of the animation.
   */
  public double getSpeed() {
    return speed;
  }


  /**
   * Change the speed of the animation.
   *
   * @param newSpeed the new speed that animation's speed will be changed to.
   */
  public void setSpeed(double newSpeed) {
    if (newSpeed >= 1) {
      this.timer.setDelay((int) ((1000 / newSpeed)));
      this.speed = newSpeed;
    }
  }

  /**
   * Check if the animation is looped or not.
   */
  public boolean getLoop() {
    return looped;
  }

  /**
   * Switches animation to a looped one if not looped.
   * Or to a not looped one if it is looped.
   */
  public void changeLoop() {
    if (this.looped) {
      this.looped = false;
    } else {
      this.looped = true;
    }
  }


}
