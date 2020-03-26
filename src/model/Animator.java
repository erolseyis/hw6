package model;

import java.util.Map;

/**
 * Represents a general animator tool that models an animation by storing shapes and their key
 * moments in the animation.
 */
public interface Animator {

  /**
   * Adds a shape to the animator.
   *
   * @param type The shape to be added.
   * @param name The name of the shape to be added.
   * @throws IllegalArgumentException If the type or name of the shape is null, or if a shape with
   *                                  the same name already exists in the animation.
   */
  void addShape(ShapeType type, String name);

  /**
   * Adds a KeyFrame to the animator for a specific element of the animation.
   *
   * @param name     The name of the element that a KeyFrame is being inserted for.
   * @param tick     The time value for the KeyFrame.
   * @param keyFrame A KeyFrame to add for the specified element.
   * @throws IllegalArgumentException If a shape with the given name does not exist in the animator
   *                                  or if the given name is null, or if the tick is negative.
   */
  void addKeyFrame(String name, int tick, KeyFrame keyFrame);

  /**
   * Returns the type of the shape with the given name.
   *
   * @param name The name of the shape to get the type of.
   * @return The ShapeType of the shape with the given name.
   */
  ShapeType getShape(String name);


  /**
   * Gets the state of all the shapes in the animator at a given tick.
   *
   * @param tick The tick we should get the states of the shapes at.
   * @return A map of shape names to their states at the given tick.
   * @throws IllegalArgumentException If the tick is negative.
   */
  Map<String, KeyFrame> getShapesAtTick(int tick);

}
