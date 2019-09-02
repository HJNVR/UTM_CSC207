package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
/**
 * Shape is parent class of all the shapes 
 * @param name  the shape we want to draw
 * @param color  the color we want the shape to be in
 */
public class Shape {
	private String name;
	private Color color;
	//COnstructor of class Shape
	public Shape( String name, Color color) {
		this.name = name;
		this.color = color;
	}
	
	public String getName() {
		return name;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return this.color;
	}
}
