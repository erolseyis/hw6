import org.junit.Test;
import view.IView;
import view.TextView;

/**
 * A class to test TextualView.
 */
public class TextViewTest {

  /**
   * Tries to modify the animation speed of the text view.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void modifySpeed() throws Exception {
    IView text = new TextView();
    text.modifyAnimationSpeed(5);
  }

}
