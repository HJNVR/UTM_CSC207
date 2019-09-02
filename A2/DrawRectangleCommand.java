package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class DrawRectangleCommand implements DrawingCommand{
	private PaintPanel paintpanel;
	private PaintModel model;
	
	public DrawRectangleCommand(PaintPanel paintpanel, PaintModel model) {
		this.paintpanel = paintpanel;
		this.model = model;
	}

	@Override
	public void draw(GraphicsContext g) {
		// draw real_time rectangle
		Rectangle r_realtime = this.model.getR_realtime();
		g.setStroke(r_realtime.getColor());
		g.strokeRect(r_realtime.getVertex().getX(), r_realtime.getVertex().getY(),
						r_realtime.getWidth(),r_realtime.getHeight());

		// Draw Rectangle
		ArrayList<Rectangle> rectangles = this.model.getRectangle();
		for (Rectangle r : rectangles) {
			double x = r.getVertex().getX();
			double y = r.getVertex().getY();
			double width = r.getWidth();
			double height = r.getHeight();
					
			g.setStroke(r.getColor());
			g.strokeRect(x, y, width, height);
					
		}
				
		// draw solid Rectangle
		ArrayList<Rectangle> rectangles_solid = this.model.getRectangle_solid();
			for (Rectangle r : rectangles_solid) {
				double x = r.getVertex().getX();
				double y = r.getVertex().getY();
				double width = r.getWidth();
				double height = r.getHeight();
					
				g.setFill(r.getColor());
				g.fillRect(x, y, width , width);
					
			}
				
				
				
		// draw outlined rectangles
		ArrayList<Rectangle> rectangles_outline = this.model.getRectangle_outline();
		for (Rectangle r : rectangles_outline) {
			double x = r.getVertex().getX();
			double y = r.getVertex().getY();
			double width = r.getWidth();
			double height = r.getHeight();
					
					
			g.setStroke(r.getColor());
			g.strokeRect(x, y, width, height);
					
			//default outline distance is 10 for coordinate and 20 for width
			g.strokeRect(x-10, y-10, width+20, height+20);
			
		}
				
		
	}
		
}