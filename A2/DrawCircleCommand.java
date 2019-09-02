package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class DrawCircleCommand implements DrawingCommand{
	private PaintPanel paintpanel;
	private PaintModel model;
	
	public DrawCircleCommand(PaintPanel paintpanel, PaintModel model) {
		this.paintpanel = paintpanel;
		this.model = model;
	}
	
	@Override
	public void draw(GraphicsContext g) {
		
		// Draw Circles feedback
		Circle c_realtime = this.model.getC_realtime();
		
		g.setStroke(c_realtime.getColor());
		g.strokeOval(c_realtime.getCentre().getX(), c_realtime.getCentre().getY() , 2 * c_realtime.getRadius(), 2 *c_realtime.getRadius());

		// Draw Circles
		ArrayList<Circle> circles = this.model.getCircles();
		for (Circle c : circles) {
			double x = c.getCentre().getX();
			double y = c.getCentre().getY();
			double radius = c.getRadius();
			
			g.setStroke(c.getColor());
			g.strokeOval(x, y , 2 * radius, 2 *radius);
		}
		
		// draw solid circles
		ArrayList<Circle> circles_solid = this.model.getCircles_solid();
		for (Circle c : circles_solid) {
			double x = c.getCentre().getX();
			double y = c.getCentre().getY();
			double radius = c.getRadius();
			
			g.setFill(c.getColor());
			g.fillOval(x, y , 2 * radius, 2 *radius);
		}
		// draw outlined circles
		ArrayList<Circle> circles_outline = this.model.getCircles_outline();
		for (Circle c : circles_outline) {
			double x = c.getCentre().getX();
			double y = c.getCentre().getY();
			double radius = c.getRadius();
			
			//g.setLineWidth(c.getThickness());
			g.setStroke(c.getColor());
			g.strokeOval(x, y , 2 * radius, 2 *radius);
			//default outline distance is 10 for coordinate and 20 for width
			g.strokeOval(x-10, y -10 , 2 * radius + 20, 2 * radius +20);
		}
		
	}
}
