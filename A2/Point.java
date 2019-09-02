package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
/**
 * Class point defines how points in this algorithm should be like
 * @param x  the x value of the point 
 * @param y  the y value of the point
 * @param color each point has a unique color
 */
public class Point {
	double x;
	double y;
	private Color color;
	//Constructor of Point
	public Point(double d, double e, Color color) {
		this.x = d;
		this.y = e;
		this.color = color;
	}

	//Second constructor of class Point

	public Point(double d, double e) {
		this.x = d;
		this.y = e;
		this.color = Color.WHITE;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return this.color;
	}

}
