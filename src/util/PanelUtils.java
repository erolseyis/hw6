package util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;


/**
 * Class that resides dimension constants of JPanel's for Visualization.
 */
public class PanelUtils {

  //Window Dimensions
  public static int WINDOW_WIDTH = 1000;
  public static int WINDOW_HEIGHT = 1000;
  public static Dimension PANEL_DIMENSION = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);

  //Button Dimensions
  public static int BUTTON_WIDTH = 50;
  public static int BUTTON_HEIGHT = 40;
  public static Dimension BUTTON_DIMENSION = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);

  //Button Margins
  public static int MARGIN_SET = 0;
  public static Insets BUTTON_INTSET = new Insets(MARGIN_SET, MARGIN_SET, MARGIN_SET, MARGIN_SET);

  //Button Font
  public static int FONT_SIZE = 20;
  public static Font BUTTON_FONT = new Font("TimesRoman", Font.BOLD, FONT_SIZE);






}

