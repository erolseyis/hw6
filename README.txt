README for the Assignment 6.
- Zach Galaez and Arman Tokgoz.

CHANGES MADE:
 - Added an interface IView and its children classes
   VisualView, TextualView and SVGView.
 - Added Panel class that extends JFrame for drawing purposes.
 - Utility package has been added. That has AnimationReader and AnimationBuilder classes to read
animation files.
 - Math Utils Class has been added to deal with some of the math operations in the animator.
 - Excellence class and main method has been added to read command line arguments.
 - CanvasDims class has been added to store the dimensions of a bounding box for an animation.
 - getShapeKeyFrames, getShapesAtTick, removeMotion, getCanvasDims, setCanvasDims methods has been
added to BasicAnimator class.



DESIGN:
 - Submitted program follows the MVC design pattern.
 - Model keeps every information about the animation.
 - View is used to visualize the data from the model.
