package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

/**
 * Square is a sub class of shape and we are using factory design pattern
 * @param vertex  we need a left corner vertex to draw to locate the square 
 * @param width   square has equal sides
 */

public class Square extends Shape{
	private Point vertex;
	private double width;
	private Color color;
	
	//constructor of Square
	public Square(Point vertex, double width, Color color){
		super("Square",color);
		this.vertex = vertex;
		this.width = width;
		this.color = color;
	}
	//extends the constructor from parent class Shape, satisfying the need for Factory design pattern
	public Square() {
		super("Square",Color.WHITE);
		this.vertex= new Point(0,0);
		this.width = 0;
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

}
