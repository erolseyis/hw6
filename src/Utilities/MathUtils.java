package Utilities;

public class MathUtils {

  /**
   * Calculates the linearly interpolated value between two points at a point between them.
   *
   * @param x1 First point x coordinate.
   * @param y1 First point y coordinate.
   * @param x2 Second point x coordinate.
   * @param y2 Second point y coordinate.
   * @param x Point to create an interpolated y value for.
   * @return The interpolated y value at the given x between the two points.
   * @throws IllegalArgumentException When slope between the points is undefined.
   */
  public int interpolate(int x1, int y1, int x2, int y2, int x) {
    double dy = (double) y2 - y1;
    double dx = (double) x2 - x1;
    if (dx == 0) {
      throw new IllegalArgumentException("undefined slope");
    }
    double slope = dy/dx;
    return (int) (y1 + slope * (double)(x - x1));
  }
}
