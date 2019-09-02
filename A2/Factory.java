package ca.utoronto.utm.paint;
 /**a class that creates new shapes according to what value parameter shape is
  * @param shape the shape we are going to create
  */
public class Factory{
	public Shape createShape(String shape){
	if(shape == "Circle")return new Circle();
	if(shape == "Rectangle")return new Rectangle();
	if(shape == "Square")return new Square();
	if(shape == "Squiggle")return new Squiggle();
	if(shape == "Eraser")return new Eraser();
	if(shape == "Polyline")return new Polyline(); 
	return null;
	}
}
	
