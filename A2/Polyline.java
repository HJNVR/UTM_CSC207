package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
/**
 * Polyline class is a sub class of Shape and we are using factory design pattern
 * @param pointStart where the line starts
 * @param pointEnd where the line ends
 * @param color each line has a unique color
 * polyline does not have style
 * and we can choose color to draw polylines
 */
public class Polyline extends Shape{
private Point pointStart;
private Point pointEnd;
private Color color;
//constructor of Polyline
public Polyline(Point pointStart, Point pointEnd, Color color) {
	super("Polyline", color);
	this.pointStart = pointStart;
	this.pointEnd = pointEnd;
	this.color = color;
}
//extends the constructor from parent class Shape, satisfying the need for Factory design pattern
public Polyline() {
	super("Polyline", Color.WHITE);
	this.pointEnd = new Point(0,0);
	this.pointStart= new Point(0,0);
}

public Point getStartPoint() {
	return this.pointStart;
}

public void setStartPoint(Point point) {
	this.pointStart = point;
}


public Point getEndPoint() {
	return this.pointEnd;
}

public void setEndPoint(Point point) {
	this.pointEnd = point;
}

}
