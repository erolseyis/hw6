import org.junit.Test;
import view.IView;
import view.SvgView;

/**
 * A class to test SvgView.
 */
public class SvgTest {


  /**
   * Tries to modify the animation speed of the svg view.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void modifySpeed() throws Exception {
    IView svg1 = new SvgView();
    svg1.modifyAnimationSpeed(5);
  }

}
