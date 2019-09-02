package ca.utoronto.utm.paint;
import javafx.scene.paint.Color;
/**
 * Eraser class is a sub class of Shape and we are using factory design pattern
 * @param vertex to use a eraser we need a left top corner vertex to know where we start to erase
 * @param width the width of a eraser
 * @param height the height of a eraser
 */
public class Eraser extends Shape{
	
	private Point vertex;
	private double width;
	private double height;
	
	public Eraser (Point vertex) {
		super("Eraser",Color.WHITE);
		this.vertex = vertex;
		this.width = 9;
		this.height = 9;
		
	}
	
	public Eraser() {
		super("Eraser",Color.WHITE);
		this.vertex = new Point(0,0);
		this.width = 9;
		this.height = 9;
	}
	
	public Point getVertex(){
		return this.vertex;
	}
	
	public void setVertex(Point vertex){
		this.vertex = vertex;
	}

}
