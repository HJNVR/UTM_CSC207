package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
/**
 * Circle is a sub class of shape and we are using factory design pattern
 * @param centre to draw a circle we need its centre to locate it
 * @param radius the radius of the circle, defining the size of the circle
 * each circle has a unique color
 */
public class Circle extends Shape{
	private Point real_centre;
	private Point centre;
	private double radius;
	private int thickness = 0;
	
	//constructor of Circle
	public Circle(Point centre, double radius, Color color) {
		super("Circle",color);
		this.centre = centre;
		this.radius = radius;
	}
	//extends the constructor from parent class Shape, satisfying the need for Factory design patter
	public Circle() {
		super("Circle", Color.WHITE);
		this.centre= new Point(0,0);
		this.radius = 0;
		this.thickness = 0;
	}
	
	public Point getrealCentre() {
		return this.real_centre;
	}
	public void setrealCentre(Point c) {
		this.real_centre = c;
	}
	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius2) {
		this.radius = radius2;
	}
	
	public void setThickness(int i) {
		this.thickness = i;
	}
	
	public int getThickness() {
		return this.thickness;
	}

}
