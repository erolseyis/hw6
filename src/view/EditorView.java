package view;


import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Animator;
import util.PanelUtils;


public class EditorView extends VisualView {

  AnimationPanel animationPanel;

  /**
   * Constructs an EditorView.
   */
  public EditorView() {
    super();
  }

  @Override
  public void render(Animator model, int ticksPerSecond) {
    this.animationPanel = new AnimationPanel(model);
    this.modifyAnimationSpeed(ticksPerSecond);
    this.add(new JScrollPane(animationPanel));

    //Scroll Bar
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);

    //Buttons
    JPanel buttonSet = new JPanel();
    buttonSet.setPreferredSize(PanelUtils.BUTTON_DIMENSION);
    this.add(buttonSet, BorderLayout.SOUTH);


    // Playback options are handled by the view alone due to our previous design decision to have
    // The view store the tick and tick speed. The model is not involved, so this should be okay.

    // Start/restart Button
    JButton startButton = new JButton("start/restart");
    startButton.setFont(PanelUtils.BUTTON_FONT);
    startButton.setPreferredSize(PanelUtils.BUTTON_DIMENSION);
    startButton.addActionListener(e -> {
      animationPanel.setTick(0);
      animationPanel.play();
    });
    buttonSet.add(startButton);

    JButton pause = new JButton("pause");
    pause.setFont(PanelUtils.BUTTON_FONT);
    pause.setPreferredSize(PanelUtils.BUTTON_DIMENSION);
    pause.addActionListener(e -> animationPanel.pause());
    buttonSet.add(pause);

    JButton play = new JButton("play");
    play.setFont(PanelUtils.BUTTON_FONT);
    play.setPreferredSize(PanelUtils.BUTTON_DIMENSION);
    play.addActionListener(e -> animationPanel.play());
    buttonSet.add(play);


    JButton faster = new JButton("faster");
    faster.setFont(PanelUtils.BUTTON_FONT);
    faster.setPreferredSize(PanelUtils.BUTTON_DIMENSION);
    faster.addActionListener(e -> animationPanel.faster());
    buttonSet.add(faster);

    JButton slower = new JButton("slower");
    faster.setFont(PanelUtils.BUTTON_FONT);
    faster.setPreferredSize(PanelUtils.BUTTON_DIMENSION);
    faster.addActionListener(e -> animationPanel.slower());
    buttonSet.add(slower);

    JToggleButton looping = new JToggleButton("loop?");
    looping.setFont(PanelUtils.BUTTON_FONT);
    looping.setPreferredSize(PanelUtils.BUTTON_DIMENSION);
    looping.addActionListener(e -> animationPanel.toggleLooping());
    buttonSet.add(slower);
    
    this.pack();
    this.setVisible(true);
  }

  public void setListener(ActionListener listener) {

  }
}
