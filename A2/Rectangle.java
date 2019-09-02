package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

/**
 * Rectangle is a sub class of shape and we are using factory design pattern
 * @param vertex to draw a rectangle we need a left top corner vertex to locate it
 * @param width the width of a rectangle
 * @param height the height of a rectangle
 * each rectangle has a unique color
 */

public class Rectangle extends Shape{
	private Point vertex;
	private double width;
	private double height;
	private Color color;
	
	//constructor of Rectangle
	
	public Rectangle(Point vertex, double width, double height, Color color){
		super("Rectanlge",color);
		this.vertex = vertex;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	//extends the constructor from parent class Shape, satisfying the need for Factory design pattern
	public Rectangle() {
		super("Rectangle",Color.WHITE);
		this.vertex= new Point(0,0);
		this.width = 0;
		this.height = 0;
	}
	
	
	public Point getVertex(){
		return this.vertex;
	}
	
	public void setVertex(Point vertex){
		this.vertex = vertex;
	}
	
	public double getWidth(){
		return this.width;
	}

	public void setWidth(double width){
		this.width = width;
	}
	public double getHeight(){
		return this.height;
	}
	public void setHeight(double height){
		this.height = height;
	}
	
}
