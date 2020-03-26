package model;

/**
 * Represents a 2D Cartesian position.
 */
public class Position {
  private final int x;
  private final int y;

  /**
   * Constructs a Position.
   *
   * @param x The x coordinate.
   * @param y The y coordinate.
   */
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    return "\tX: " + this.x + "\tY: " + this.y;
  }
}
