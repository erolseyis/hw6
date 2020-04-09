import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.naming.OperationNotSupportedException;

import controller.Controller;
import model.Animator;
import model.BasicAnimator;
import util.AnimationReader;
import view.IView;
import view.ViewCreator;

public class Excellence {
  public static void main(String[] args) {
    String in = null;
    String viewName = null;
    String out = null;
    int speed = 1;

    for (int i = 0; i < args.length; i++) {
      if (args[i].charAt(0) == '-') {
        String argName = args[i].substring(1);
        if (argName.equals("in")) {
          in = args[i + 1];
        } else if (argName.equals("view")) {
          viewName = args[i + 1];
        } else if (argName.equals("out")) {
          out = args[i + 1];
        } else if (argName.equals("speed")) {
          speed = Integer.parseInt(args[i + 1]);
        } else {
          throw new IllegalArgumentException("invalid arg name");
        }
      }
    }
    Readable r;
    Appendable w = System.out;

    try {
      r = new FileReader(in);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("in file doesn't exist");
    }

    if (out != null) {
      try {
        w = new FileWriter(out);
      } catch (IOException e) {
        throw new IllegalArgumentException("couldn't open out file");
      }
    }

    Animator animation = AnimationReader.parseFile(r, new BasicAnimator.BasicAnimationBuilder());
    IView view = ViewCreator.create(viewName);
    Controller c = new Controller(animation, view, speed, w);
  }
}