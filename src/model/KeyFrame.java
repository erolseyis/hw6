package model;

import java.awt.Color;

/**
 * Represents the attributes and location of one of the added shapes at a specific point in time. We
 * assume the that all shapes allowed in an animation can be represented the same way, which allows
 * us to simplify our code considerably. More specifically, we can have the Animator classes deal
 * with the types of shapes being created, while this class just stores their change in state,
 * making things easier.
 */
public class KeyFrame {
  public final Color color;
  public final int w;
  public final int h;
  public final Position position;

  /**
   * Constructs a KeyFrame.
   *
   * @param color    The color of the shape.
   * @param w        The width of the shape.
   * @param h        The height of the shape.
   * @param position Where the shape should appear on the scene.
   * @throws IllegalArgumentException If given null arguments or an invalid tick or dimensions.
   */
  public KeyFrame(Color color, int w, int h, Position position) {
    if (color == null) {
      throw new IllegalArgumentException("color cannot be null");
    }
    if (w < 0) {
      throw new IllegalArgumentException("shape height cannot be negative");
    }
    if (h < 0) {
      throw new IllegalArgumentException("shape width cannot be negative");
    }
    if (position == null) {
      throw new IllegalArgumentException("position cannot be null");
    }

    this.color = color;
    this.w = w;
    this.h = h;
    this.position = position;
  }

  @Override
  public String toString() {
    return this.position.toString() +
            "\tHeight: " + this.h +
            "\tWidth: " + this.w +
            "\tColor: " + this.color.toString();
  }
}
