package view;

/**
 * Factory class for creating a view of the desired type.
 */
public class ViewCreator {

  /**
   * A factory method to create the appropriate view.
   *
   * @param viewType The type of view to create.
   * @return An instance of the appropriate view depending on the specified type.
   */
  public static IView create(String viewType) {
    if (viewType == null) {
      throw new IllegalArgumentException("view type cannot be null");
    }
    switch (viewType) {
      case "text":
        return new TextView();
      case "svg":
        return null;
      case "visual":
        return new VisualView();
      default:
        throw new IllegalArgumentException("unsupported view type");
    }
  }
}
