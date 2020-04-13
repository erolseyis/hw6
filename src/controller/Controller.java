package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.Animator;
import model.ReadOnlyModel;
import view.EditorView;
import view.IView;

/**
 * Controls app and communication between view and model.
 */
public class Controller implements ActionListener {

  private Animator model;
  private IView view;



  /**
   * Constructs a Controller.
   * @param model The animation model.
   * @param view The animation view.
   * @param speed The tick rate.
   * @param out The output location.
   */
  public Controller(Animator model, IView view, int speed, Appendable out) {
    this.model = model;
    this.view = view;
    view.setListener(this);
    view.setOutput(out);
    view.render(new ReadOnlyModel(this.model), speed);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
//    switch (e.getActionCommand()) {
//      case
//    }
  }


}
