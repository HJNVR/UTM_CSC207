package ca.utoronto.utm.paint;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * concrete command of command 
 *
 *
 */
public class MousePressed_Command implements Mouse_Command, EventHandler<MouseEvent>{
	private PaintPanel paintpanel;
	public MousePressed_Command(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
	}
	@Override
	public void handle(MouseEvent event) {
		this.execute(event);
		
	}
	
	
	@Override
	public void execute(MouseEvent e) {
		if (this.paintpanel.getCurrentShpae().getName() == "Squiggle") {
			
			
	}else if (this.paintpanel.getCurrentShpae().getName()  == "Circle") {
		Point centre = new Point(e.getX(), e.getY());
		double radius = 0;
		// to create a new circle in paintpanel
		
		Color color = this.getColor();
		Circle c = new Circle(centre,radius,color);
		int thickness = this.getLineThickness();
		c.setThickness(thickness);
		c.setrealCentre(centre);
		this.paintpanel.setCircle(c);
		
	}else if(this.paintpanel.getCurrentShpae().getName() == "Rectangle"){
		Point vertex = new Point( e.getX(),  e.getY());
		double width = 0.0;
		double height = 0.0;
		Color color = this.getColor();
		Rectangle r = new Rectangle(vertex,width,height, color);
		this.paintpanel.setRectangle(r);
		
		}else if(this.paintpanel.getCurrentShpae().getName() == "Square"){
			Point vertex = new Point( (int)e.getX(),  (int)e.getY());
			double width = 0.0;

			Color color = this.getColor();
			Square s = new Square(vertex, width, color);
			this.paintpanel.setSquare(s);
    
	
	}else if(this.paintpanel.getCurrentShpae().getName() == "Eraser"){
		Point vertex = new Point( e.getX(),  e.getY());
		Eraser eraser = new Eraser(vertex);
		this.paintpanel.seteraser(eraser);
		this.paintpanel.getModel().add_clear(eraser);
	}else if(this.paintpanel.getCurrentShpae().getName() == "Polyline") {
	}
	}
	
	public Color getColor() {
		Color r = Color.BLACK;
		if (this.paintpanel.getColor() == "Red") {
			r = Color.RED;
		}else if(this.paintpanel.getColor() == "Green"){
			r = Color.GREEN;
		}else if(this.paintpanel.getColor() == "White") {
			r = Color.WHITE;
		}else if(this.paintpanel.getColor() == "Yellow") {
			r = Color.YELLOW;
		}else if(this.paintpanel.getColor() == "Blue") {
			r = Color.BLUE;
		}else if(this.paintpanel.getColor() == "Orange") {
			r = Color.ORANGE;
		}else if(this.paintpanel.getColor() == "Purple") {
			r = Color.PURPLE;
		}
		return r;
	}
	
	public int getLineThickness() {
		if(this.paintpanel.getThickness() != null) {
			return Integer.parseInt(this.paintpanel.getThickness());
		}
		return 0;
	}
	}
	
